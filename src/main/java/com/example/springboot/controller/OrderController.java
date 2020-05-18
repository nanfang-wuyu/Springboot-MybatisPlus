package com.example.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Interval;
import com.example.springboot.entity.Order;
import com.example.springboot.mapper.IntervalMapper;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.service.IntervalService;
import com.example.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/queryUserOrders/{userId}")
    public List<JSONObject> queryUserOrders(@PathVariable BigInteger userId){
        return orderService.queryUserOrders(userId);
    }

    @GetMapping("/cancelOrder/{orderId}")
    public boolean cancelOrder(@PathVariable BigInteger orderId){
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/payOrder/{orderId}")
    public boolean payOrder(@PathVariable BigInteger  orderId){
        return orderService.payOrder(orderId);
    }

}
