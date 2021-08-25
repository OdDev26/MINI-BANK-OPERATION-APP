package com.example.newwork.repository;

import com.example.newwork.enums.ClientStatus;
import com.example.newwork.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findClientByStatus(ClientStatus status);
}
