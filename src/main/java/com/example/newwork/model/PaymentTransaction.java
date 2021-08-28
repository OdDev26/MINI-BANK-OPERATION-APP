package com.example.newwork.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue(value = "Payment")
public class PaymentTransaction extends Transaction {
    public PaymentTransaction() {
        super();
    }
    public PaymentTransaction(Date transactionDate, double amount, Account account) {
        super(transactionDate, amount, account);
    }
}
