package controller;

import model.Account;
import model.service.AccountService;
import view.AccountView;

import java.sql.SQLException;

public class AccountController {

    private final AccountView view;
    private final AccountService service;


    public AccountController(AccountView view, AccountService service) {
        this.view = view;
        this.service = service;
    }

    public void createAccount() {
        Account newAccount = view.showAccountCreation();
        try {
            service.createAccount(newAccount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
