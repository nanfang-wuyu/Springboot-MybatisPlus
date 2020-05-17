package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Interval;
import com.example.springboot.entity.Order;
import com.example.springboot.mapper.IntervalMapper;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.service.IntervalService;
import com.example.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    



}
