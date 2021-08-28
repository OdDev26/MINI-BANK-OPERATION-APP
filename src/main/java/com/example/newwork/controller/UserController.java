package com.example.newwork.controller;

import com.example.newwork.enums.ClientStatus;
import com.example.newwork.model.Client;
import com.example.newwork.model.User;
import com.example.newwork.paymentDto.UserDTO;
import com.example.newwork.repository.ClientRepository;
import com.example.newwork.repository.UserRepository;
import com.example.newwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
   private ClientRepository repository;
@Autowired
    private UserService service;
@Autowired
private UserRepository userRepository;




    @GetMapping(value = "/user")
    public String employeeLogin(Model model){
        model.addAttribute("employeeLogin", new UserDTO());
        return "login";
    }

    @PostMapping(value = "/login")
    public String employeeLogin(@Valid UserDTO userdto, BindingResult result, HttpServletRequest request, Model model){
        User user =service.getUserByEmailAndPassword(userdto.getEmail(), userdto.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute("userLog", user);
            System.out.println(session.getId());
            System.out.println(userdto.getPassword());
            model.addAttribute("login", userdto);

                return "redirect:/userAccount";
        }




    @GetMapping(value = "/grantClient/{id}")
    public  String approveRequest(@PathVariable(name = "id") Long id, HttpServletRequest request){


        Client client = repository.getById(id);
        client.setStatus(ClientStatus.ACTIVE);
        repository.save(client);
        User user = new User();
        HttpSession session = request.getSession();
        user.setUserFirstName(client.getFirstName());
        user.setUserMiddleName(client.getMiddleName());
        user.setUserLastName(client.getLastName());
        user.setUserEmail(client.getClientEmailAddress());
        user.setUserPassword(client.getPassword());
        user.setUserAddress(client.getClientAddress());
        user.setAccountType(client.getAccountType());
        user.setUserPhoneNumber(client.getPhoneNumber());
        session.setAttribute("user", user);
        service.createNewUser(user);

        return "redirect:/viewBankOpeningRequest";
    }
    @RequestMapping("/userAccount")
    public String displayUserProfile(HttpSession session, Model model){
    User user = (User) session.getAttribute("userLog");
        System.out.println(user.getUserID());
    model.addAttribute("userList", userRepository.getById(user.getUserID()));
    return "user_page";
    }
    @RequestMapping("/admin")
    public String goHome(){
        return "home";
    }

}
