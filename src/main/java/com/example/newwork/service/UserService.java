package com.example.newwork.service;

import com.example.newwork.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createNewUser(User user);
    User getUserByEmailAndPassword(String email, String password);
}
