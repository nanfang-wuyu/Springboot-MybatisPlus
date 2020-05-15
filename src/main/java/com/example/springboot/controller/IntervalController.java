package com.example.springboot.controller;

import com.example.springboot.service.IntervalService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntervalController {

    @Autowired
    private IntervalService intervalService;

    /*@GetMapping("/")*/



}
