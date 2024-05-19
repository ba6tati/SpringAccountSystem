package com.ba6tati.account.system.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ba6tati.account.system.model.UserModel;
import com.ba6tati.account.system.service.UserService;

@Controller
public class UserController {
  
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String gerLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
        System.out.println("Register request: " + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getUsername(), userModel.getEmail(), userModel.getPassword());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("Login request: " + userModel);
        boolean authenticated = userService.authenticate(userModel.getUsername(), userModel.getPassword());
        if (authenticated) {
            model.addAttribute("username", userModel.getUsername());
            return "profile";
        } else {
            return "error_page";
        }
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        // userList = List<UserModel>();
        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }
}
