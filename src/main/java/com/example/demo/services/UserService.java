package com.example.demo.services;

import com.example.demo.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);

}
