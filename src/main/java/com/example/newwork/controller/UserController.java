package com.example.newwork.controller;

import com.example.newwork.enums.ClientStatus;
import com.example.newwork.model.Client;
import com.example.newwork.model.User;
import com.example.newwork.repository.ClientRepository;
import com.example.newwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.security.*;

@Controller
public class UserController {
    @Autowired
   private ClientRepository repository;
@Autowired
    private UserService service;
@RequestMapping ("/home")
 public String displayHome()  {
     return "home";
 }

//@GetMapping("/newUser")
//    public String createNewUser(ClientStatus status){
//    status = ClientStatus.PENDING;
//        List<Client> clients = repository.findClientByStatus(status);
//        User user = new User();
//
//            for (Client client : clients){
//                    user.setUserFirstName(client.getFirstName());
//                    user.setUserMiddleName(client.getMiddleName());
//                    user.setUserLastName(client.getLastName());
//                    user.setUserEmail(client.getClientEmailAddress());
//                    user.setUserPassword(client.getPassword());
//                    user.setUserAddress(client.getClientAddress());
//                    user.setAccountType(client.getAccountType());
////                    user.setAccountNumber(randomNumber());
//                    user.setUserPhoneNumber(client.getPhoneNumber());
//                    service.createNewUser(user);
//                    break;
//                }
//
//
//
//            return "new_user_created";
//    }

    public static  String randomNumber(){
    SecureRandom random = new SecureRandom();
    String accountNum = "";
    for (int counter = 0; counter <=4; counter++)
        accountNum += 233 + random.nextInt(10);
        return accountNum;
    }
    @GetMapping(value = "/grantClient/{id}")
    public  String approveRequest(@PathVariable(name = "id") Long id){
        Client client = repository.getById(id);
        client.setStatus(ClientStatus.ACTIVE);
        repository.save(client);
        User user = new User();
        user.setUserFirstName(client.getFirstName());
        user.setUserMiddleName(client.getMiddleName());
        user.setUserLastName(client.getLastName());
        user.setUserEmail(client.getClientEmailAddress());
        user.setUserPassword(client.getPassword());
        user.setUserAddress(client.getClientAddress());
        user.setAccountType(client.getAccountType());
        user.setAccountNumber(randomNumber());
        user.setUserPhoneNumber(client.getPhoneNumber());
        service.createNewUser(user);
        return "redirect:/viewBankOpeningRequest";
    }
}
