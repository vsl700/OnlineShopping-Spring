package com.vsl700.onlineshopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vsl700.onlineshopping.services.AccountService;

@Controller
public class LoginRegisterController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String loginView(){
        return "account/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        accountService.login(username, password);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        accountService.logout();

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerView(){
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username, @RequestParam String email, @RequestParam String password){
        accountService.register(firstName, lastName, username, email, password);

        return "redirect:/login";
    }

}
