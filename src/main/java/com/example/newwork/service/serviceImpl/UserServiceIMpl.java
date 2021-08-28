package com.example.newwork.service.serviceImpl;

import com.example.newwork.model.User;
import com.example.newwork.repository.ClientRepository;
import com.example.newwork.repository.UserRepository;
import com.example.newwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMpl implements UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    ClientRepository clientRepository;
    @Override
    public void createNewUser(User user) {
        repository.save(user);
    }


    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return repository.findUserByUserEmailAndUserPassword(email, password);
    }
}
