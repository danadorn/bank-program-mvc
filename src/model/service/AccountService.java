package model.service;

import model.Account;
import model.repository.AccountRepository;

import java.sql.SQLException;

public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount(Account account) throws SQLException {
        if (account.getOwnerName().isBlank()) {
            throw new RuntimeException("Owner name is required");
        }
        if (account.getBalance() < 0) {
            throw new RuntimeException("Balance must be greater than zero");
        }
        repository.createAccount(account);
    }
}
