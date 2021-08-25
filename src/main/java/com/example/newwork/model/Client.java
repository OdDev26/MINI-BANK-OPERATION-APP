package com.example.newwork.model;

import com.example.newwork.enums.AccountType;
import com.example.newwork.enums.ClientStatus;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;


@Data
@Entity
@Table(name = "Client_Table")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientID;
    @Column(name = "client_firstName", nullable = false)
    @NotNull
    private String firstName;
    @Column(name = "client_middleName")
    @NotNull
    private String middleName;
    @NotNull
    @Column(name = "client_lastName")
    private String lastName;
    @NotNull
    @Column(name = "client_phoneNumber")
    @Size(min = 11, max = 11)
    private String phoneNumber;
    @NotNull
    @Column(name = "client_email")
    @Email(message = "${invalid.email}")
    private String clientEmailAddress;
    @NotNull
    private String clientAddress;
    @NotNull
    private String username;
    @NotNull
    @Size(min = 6, max = 50)
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientStatus status;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}

