package com.ramesh.springsecuritydemo.controller;

import com.ramesh.springsecuritydemo.dto.UserRegistrationDto;
import com.ramesh.springsecuritydemo.model.User;
import com.ramesh.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping
    public String processRegistrationForm(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto, BindingResult result) {
        User existing = userService.findByEmail(userRegistrationDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userRegistrationDto);
        return "redirect:/registration?success";

    }
}
