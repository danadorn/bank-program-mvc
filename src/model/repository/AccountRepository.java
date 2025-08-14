package model.repository;

import model.Account;
import model.db.DbConnection;
import model.dto.TransactionResponse;
import model.dto.TransferRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    public void createAccount(Account account) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = """
                insert into accounts (owner_name, balance) values (?, ?)
                """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getOwnerName());
        ps.setDouble(2, account.getBalance());

        int rowAffected = ps.executeUpdate(); // insert statement use Updated
        if (rowAffected > 0) {
            System.out.println("Inserted successfully!");
        }else {
            System.out.println("Insertion failed!");
        }
        connection.close();

    }

    public Account findById(Integer id) throws SQLException {
        try (Connection connection = DbConnection.getInstance()) {
            String sql = """
                    select * from accounts where account_id = ?
                    """;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        return new Account(
                                rs.getInt("account_id"),
                                rs.getString("owner_name"),
                                rs.getDouble("balance")
                        );
                    }
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean existsById(Integer id) throws SQLException {
        if(!existsById(id)) {
            throw new SQLException("ID does not exist");
        }

        try(Connection connection = DbConnection.getInstance()) {
            String sql = """
                    select * from accounts where account_id = ?
                    """;
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next(); // if our sql execute return 1 row and 1 column, it will return true
                }
            }
        }
    }

    public void transferMoney(TransferRequest request) throws SQLException {
        try(Connection connection = DbConnection.getInstance()) {
            connection.setAutoCommit(false); // start transaction performance
            try {
                // deduct money from sender
                String sql = """
                update accounts 
                set balance = balance - ? 
                where account_id = ?
                """;

                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setDouble(1, request.amount());
                    ps.setInt(2, request.fromAccountId());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error send money!");
                    }
                }

                // add money to receiver
                String sql2 = """
                update accounts 
                set balance = balance + ? 
                where account_id = ?
                """;

                try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                    ps.setDouble(1, request.amount());
                    ps.setInt(2, request.toAccountId());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error send money!");
                    }
                }

                // add transaction record
                String sql3 = """
                insert into accounts (owner_name, balance) 
                values (?, ?)
                """;
                try (PreparedStatement ps = connection.prepareStatement(sql3)) {
                    ps.setInt(1, request.fromAccountId());
                    ps.setInt(2, request.toAccountId());
                    ps.setDouble(3, request.amount());

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        throw new SQLException("Error save transaction record!");
                    }
                }
                connection.commit();

            }catch (SQLException e) {
                connection.rollback();
            }finally {
                connection.setAutoCommit(true);
            }
        }









    }

    public List<TransactionResponse> viewTransactionDetails() throws SQLException {
        try (Connection connection = DbConnection.getInstance()) {
            String sql = """
                    select t.transaction_id as id,
                    sender.owner_name as sender_name, 
                    receiver.owner_name as receiver_name, 
                    t.amount as transfer_amount,
                    t.created_at as timestamp
                    from transactions t 
                    join accounts sender
                    on t.from_account = sender.account_id
                    join accounts receiver
                    on t.to_account = receiver.account_id
                    """;
            try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                List<TransactionResponse> responses = new ArrayList<>();
                while (rs.next()) {
                    responses.add(new TransactionResponse(
                            rs.getInt("id"),
                            rs.getString("sender_name"),
                            rs.getString("receiver_name"),
                            rs.getDouble("transfer_amount"),
                            String.valueOf(
                                    rs.getTimestamp("timestamp")
                            )
                    ));
                }
            }
        }
        return null;
    }

}


