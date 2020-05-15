package com.example.springboot.controller;

import com.example.springboot.entity.Station;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser{name,phone,card}")
    public void addUser(@PathVariable String name, BigInteger phone, BigInteger card){
        userService.addUser(name, phone, card);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User helloUser() {
        User user = userService.getUserById(1);
        System.out.println(user);
        return user;
    }


}
