package controller;

import model.Account;
import model.dto.TransferRequest;
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

    public void findAccountById() {
        Integer id = view.showEnterId();
        Account account = service.findById(id);
        view.showAccountDetails(account);
    }

    public void transferMoney() {
        TransferRequest request = view.showTransferRequest();
        service.transferMoney(request);
    }

    public void showTransactionRecords() {
        view.showTransactionRecord(
                service.getTransactions()
        );
    }
}
