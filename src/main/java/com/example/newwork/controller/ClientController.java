package com.example.newwork.controller;

import com.example.newwork.enums.ClientStatus;
import com.example.newwork.model.Client;
import com.example.newwork.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientService service;
    @RequestMapping(value = "/")
    public String clientRegistration(Model model){
        Client client = new Client();
        model.addAttribute("client", client);

        return "index";
    }

    @PostMapping("/saveNewClient")
    public String saveRegistration(@ModelAttribute("client") Client client, Model model ){
        client.setStatus(ClientStatus.PENDING);
        service.createNewUser(client);
        return "new_user_created";
    }

    @RequestMapping(value = "/viewBankOpeningRequest")
    public ModelAndView viewAllLeaveRequest(){
        ModelAndView modelAndView = new ModelAndView("new_user_created");
        List<Client> applicantList = service.displayAllClient();
        modelAndView.addObject("applicantList", applicantList);
        return modelAndView;
    }
}
