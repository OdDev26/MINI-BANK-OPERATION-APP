package com.example.newwork.controller;

import com.example.newwork.enums.AccountType;
import com.example.newwork.enums.TransactionType;
import com.example.newwork.model.Account;
import com.example.newwork.model.User;
import com.example.newwork.repository.AccountRepository;
import com.example.newwork.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class BankAccountController {
    @Autowired
    BankAccountService service;

    double balance = 0.00;

    @RequestMapping(value = "/saveAccount")
    public String saveEmployee(HttpSession session){
        Account account = new Account();
        account.setAccountType(AccountType.SAVINGS);
        User user = (User) session.getAttribute("user");
        account.setUser(user);
        account.setAccountNumber(randomNumber());
        account.setBalance(balance);
        account.setCreateDate(new Date());

        service.createAccount(account);
        return "redirect:/viewBankOpeningRequest";
    }
    public static  String randomNumber(){
        SecureRandom random = new SecureRandom();
        String accountNum = "";
        for (int counter = 0; counter <=4; counter++)
            accountNum += 233 + random.nextInt(10);
        return accountNum;
    }


    @PostMapping("/payment")
    public String accountDetails(@RequestParam (value = "account_number") String accountNumber,
                                 @RequestParam(defaultValue = "0") double transactionAmount, Model model,
                                HttpSession session){
        System.out.println(accountNumber);
    Account account = service.getAccountByAccountNumber(accountNumber);
    service.payToAccount(accountNumber, transactionAmount);
        model.addAttribute("current", account);
    return "account";
    }

    @GetMapping("/gotoPay")
    public String displayAccount(HttpSession session,Model model){
        User user = (User) session.getAttribute("userLog");
        System.out.println(user.getUserID());
        List<Account> list = (List<Account>) user.getAccounts();
        model.addAttribute("accountList",list);
        return "payment_page";
    }
    @GetMapping("/justCustomer")
    public String justCustomerPage(){
        return "user_page";
    }

    @PostMapping("/withdraw")
    public String accountWithdraw(@RequestParam (value = "account_number") String accountNumber,
                                 @RequestParam(defaultValue = "0") double transactionAmount, Model model,
                                 HttpSession session){
        System.out.println(accountNumber);
        Account account = service.getAccountByAccountNumber(accountNumber);
        service.removeFromAccount(accountNumber, transactionAmount);
        model.addAttribute("current", account);
        return "account";
    }

    @GetMapping("/gotoWithdraw")
    public String displayWithdrawAccount(HttpSession session,Model model){
        User user = (User) session.getAttribute("userLog");
        System.out.println(user.getUserID());
        List<Account> list = (List<Account>) user.getAccounts();
        model.addAttribute("accountList",list);
        return "withdraw_page";
    }

    @PostMapping("/transfer")
    public String accountTransfer(@RequestParam (value = "account_number") String fromAccountNumber,
                                  @RequestParam(defaultValue = "0") double transactionAmount, Model model,
                                  HttpSession session, @RequestParam (value = "recipient_account_number") String toAccountNumber){
        System.out.println(fromAccountNumber);
        System.out.println(toAccountNumber);
        Account account = service.getAccountByAccountNumber(fromAccountNumber);
       service.transfer(fromAccountNumber,toAccountNumber, transactionAmount);
        model.addAttribute("current", account);
        return "account";
    }

    @GetMapping("/gotoTransfer")
    public String displayTransfer(HttpSession session,Model model){
        User user = (User) session.getAttribute("userLog");
        System.out.println(user.getUserID());
        List<Account> list = (List<Account>) user.getAccounts();
        model.addAttribute("accountList",list);
        return "transfer_page";
    }

}
