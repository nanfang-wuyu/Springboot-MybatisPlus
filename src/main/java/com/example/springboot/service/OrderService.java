package com.example.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Interval;
import com.example.springboot.entity.Order;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component(value = "orderService")
public interface OrderService extends IService<Order> {


    @Options(useGeneratedKeys = true,keyProperty = "order_id",keyColumn = "order_id")
    BigInteger addOrder(BigInteger userId, Date creatDate, Time createTime, String status, double price);


    List<JSONObject> queryUserOrders(BigInteger userId);
}
