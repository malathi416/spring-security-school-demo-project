package com.ramesh.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/home")
    public String home1() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

//    @GetMapping("/user")
//    public String userIndex() {
//        return "user/home";
//    }
}
