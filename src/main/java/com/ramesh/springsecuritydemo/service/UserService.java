package com.ramesh.springsecuritydemo.service;

import com.ramesh.springsecuritydemo.dto.UserRegistrationDto;
import com.ramesh.springsecuritydemo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
    User findByEmail(String email);
}
