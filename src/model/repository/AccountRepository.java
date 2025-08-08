package model.repository;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepository {

    private Connection connection;
    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    public void createAccount(Account account) throws SQLException {
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
}
