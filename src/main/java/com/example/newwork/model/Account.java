package com.example.newwork.model;

import com.example.newwork.enums.AccountType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "account_id")
    private Long accountID;

    private Date createDate;

    private double balance;
@Column(name = "accountNumber")
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Transaction> transactions;
}
