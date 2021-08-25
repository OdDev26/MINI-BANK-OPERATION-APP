package com.example.newwork.service.serviceImpl;

import com.example.newwork.model.Client;
import com.example.newwork.repository.ClientRepository;
import com.example.newwork.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;
    @Override
    public void createNewUser(Client client) {
        repository.save(client);
    }
    @Override
    public List<Client> displayAllClient() {
        return repository.findAll();
    }
}
