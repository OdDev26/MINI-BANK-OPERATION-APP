package com.example.newwork.model;

import com.example.newwork.enums.AccountType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "account_id")
    private Long accountID;
    private Date createDate;
    private double balance;
    private AccountType accountType;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;
}
