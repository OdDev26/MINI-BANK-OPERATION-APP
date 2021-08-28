package com.example.newwork.service.serviceImpl;

import com.example.newwork.model.Account;
import com.example.newwork.model.PaymentTransaction;
import com.example.newwork.model.WithdrawalTransaction;
import com.example.newwork.repository.AccountRepository;
import com.example.newwork.repository.TransactionRepository;
import com.example.newwork.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BankAccountServiceImpl implements BankAccountService {
 @Autowired
    AccountRepository accountRepository;
 @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
         Account account = accountRepository.findAccountByAccountNumber(accountNumber);
       if (account == null)
          throw new RuntimeException("Account Not Found");
        return account;
    }

    @Override
    public void payToAccount(String accountNumber, double amount) {
        Account currentAccount = getAccountByAccountNumber(accountNumber);

        PaymentTransaction paymentTransaction = new PaymentTransaction(new Date(), amount, currentAccount);
        transactionRepository.save(paymentTransaction);
        currentAccount.setBalance(currentAccount.getBalance() + amount);
        accountRepository.save(currentAccount);
    }

    @Override
    public void removeFromAccount(String accountNumber, double amount) {
        Account currentAccount = getAccountByAccountNumber(accountNumber);
        double max = 100000;
        if (amount > max){
            throw new RuntimeException("Insufficient discount ");
        }
        else if (currentAccount.getBalance() < amount)
            throw new RuntimeException("Insufficient discount ");

        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(new Date(), amount, currentAccount);
        transactionRepository.save(withdrawalTransaction);
        currentAccount.setBalance(currentAccount.getBalance() - amount);
        accountRepository.save(currentAccount);
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        if (fromAccountNumber.equals(toAccountNumber))
            throw new RuntimeException(
                    "Impossible operation: account number must be different");
        removeFromAccount(fromAccountNumber, amount);
        payToAccount(toAccountNumber, amount);

    }
}
