package com.example.newwork.model;

import javax.persistence.*;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffID;
    @Column(name = "staff_first_name", nullable = false)
    private String staffFirstName;
    @Column(name = "staff_last_name", nullable = false)
    private String staffLastName;
    @Column(name = "staff_password", nullable = false)
    private String password;
    @Column(name = "staff_portfolio", nullable = false)
    private String portfolio;
    @Column(name = "staff_email", nullable = false)
    private String email;
}
