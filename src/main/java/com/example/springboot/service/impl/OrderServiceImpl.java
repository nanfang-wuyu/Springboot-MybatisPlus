package com.example.springboot.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Order;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private OrderService orderService;

}
