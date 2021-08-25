package com.example.newwork.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue(value = "Withdraw")
public class WithdrawalTransaction extends Transaction{
    public WithdrawalTransaction() {
        super();
    }

    public WithdrawalTransaction(Date transactionDate, Long amount, Account account) {
        super(transactionDate, amount, account);
    }
}
