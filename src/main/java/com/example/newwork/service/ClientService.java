package com.example.newwork.service;

import com.example.newwork.model.Client;

import java.util.List;

public interface ClientService {
    void createNewUser(Client client);
    List<Client> displayAllClient();
}
