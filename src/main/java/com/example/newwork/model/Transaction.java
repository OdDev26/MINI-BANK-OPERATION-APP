package com.example.newwork.model;

import com.example.newwork.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TRANSACTION_DIFFERENCE", discriminatorType = DiscriminatorType.STRING)
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionID;
    private Date transactionDate;
    private double amount;
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
@ManyToOne
@NotNull
@JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {
        super();
    }

    public Transaction(Date transactionDate, Long amount, Account account) {
        super();
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.account = account;
    }
}
