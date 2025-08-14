package view;

import model.Account;
import model.dto.TransactionResponse;
import model.dto.TransferRequest;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class AccountView {
    public static final Scanner scanner = new Scanner(System.in);

    public Account showAccountCreation(){
        System.out.print("Enter account name: ");
        String name = scanner.nextLine();

        System.out.print("Enter balance: ");
        Double balance = Double.parseDouble(scanner.nextLine());

        return new Account(name, balance);

    }

    public Integer showEnterId() {
        System.out.print("Enter account ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void showAccountDetails(Account account) {
        Table table = new Table(
                3,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        table.addCell("Account Details", 3);
        table.addCell("Account ID ");
        table.addCell(String.valueOf(account.getId()), 2);
        table.addCell("Owner name ");
        table.addCell(account.getOwnerName(), 2);
        table.addCell("Balance");
        table.addCell(String.valueOf(account.getBalance()), 2);

        System.out.println(table.render());
    }

    public TransferRequest showTransferRequest() {
        System.out.println("Enter account ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter receiver ID: ");
        int receiver = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        return new TransferRequest(id, receiver, amount);
    }

    public void showTransactionRecord(List<TransactionResponse> responses) {
        Table table = new Table(
                5,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER
        );
        table.addCell("ID");
        table.addCell("Sender name");
        table.addCell("Receiver name");
        table.addCell("Amount");
        table.addCell("Amount");
        table.addCell("Timestamp");
        responses.forEach(
                t -> {
                    table.addCell(String.valueOf(t.id()));
                    table.addCell("Sender name");
                    table.addCell("Receiver name");
                    table.addCell("Amount");
                    table.addCell("Amount");
                    table.addCell("Timestamp");

                }
        );
        System.out.println(table.render());
    }
}
