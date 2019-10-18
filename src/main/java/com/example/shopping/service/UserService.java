package com.example.shopping.service;

import com.example.shopping.model.User;
import com.example.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User addUser(User user)
    {
        return userRepository.save(user);
    }
    public Integer getUserIds(Principal principal)
    {
        String email=principal.getName();
        return userRepository.findByEmail(email).getUserid();
    }
    public User callUser(Principal principal) {
        return userRepository.findByEmail(principal.getName());
    }
}
