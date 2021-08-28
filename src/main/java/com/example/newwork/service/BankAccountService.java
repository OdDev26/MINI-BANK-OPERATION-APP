package com.example.newwork.service;

import com.example.newwork.model.Account;
import com.example.newwork.model.User;

public interface BankAccountService {
    public Account getAccountByAccountNumber(String accountId);

    void createAccount(Account account);

    public void payToAccount(String accountNumber, double amount);

    public void removeFromAccount(String accountNumber, double amount);

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount);
}
