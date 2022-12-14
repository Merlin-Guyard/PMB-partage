package com.mg.paymybuddy.controller;

import com.mg.paymybuddy.model.User;
import com.mg.paymybuddy.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegister() {
        return "register";
    }

    @PostMapping
    public String getUser(Model model,
                          @NotNull String firstName,
                          @NotNull String lastName,
                          @NotNull String email,
                          @NotNull String password) {

        //save user
        User user = new User(firstName, lastName, email, password);
        userService.addUser(user);

        return "login";
    }

}