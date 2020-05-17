package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Interval;
import com.example.springboot.entity.Order;
import org.springframework.stereotype.Component;

@Component(value = "orderService")
public interface OrderService extends IService<Order> {



}
