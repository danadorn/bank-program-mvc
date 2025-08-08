package view;

import model.Account;

import java.util.Scanner;

public class AccountView {
    public static final Scanner scanner = new Scanner(System.in);

    public Account showAccountCreation(){
        System.out.println("Enter account name: ");
        String name = scanner.nextLine();

        System.out.println("Enter balance: ");
        Double balance = scanner.nextDouble();

        return new Account(name, balance);

    }
}
