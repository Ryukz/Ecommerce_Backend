package com.example.shopping.controller;

import com.example.shopping.model.User;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping(path = "/adduser",produces = "application/json",consumes = "application/json")
    public User addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }
    @GetMapping(path ="/checkUser",produces = "application/json")
    public String checkUser() {return "\"valid\"";}

}
