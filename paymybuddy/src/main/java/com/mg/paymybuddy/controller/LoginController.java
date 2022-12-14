package com.mg.paymybuddy.controller;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLogin() {
        return "login";
    }

    @PostMapping
    public String getUser(Model model,
                          @NotNull String email,
                          @NotNull String password, Principal principal) {

//        System.out.println(email);
        return "profile";
    }
}