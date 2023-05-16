package com.vsl700.onlineshopping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRegisterController {
    
    @GetMapping("/login")
    public String loginView(){
        return "account/login";
    }

    @GetMapping("/register")
    public String registerView(){
        return "account/register";
    }

}
