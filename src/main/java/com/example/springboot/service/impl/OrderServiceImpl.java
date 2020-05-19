package com.example.springboot.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Order;
import com.example.springboot.entity.Ticket;
import com.example.springboot.entity.Train;
import com.example.springboot.entity.Train_station;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainStationService trainStationService;

    @Autowired
    private StationService stationService;


    @Override
    public BigInteger addOrder(BigInteger userId, Date creatDate, Time createTime, String status, double price){

        Order order = new Order();
        //order.setOrderId(BigInteger.valueOf(0));
        order.setUserId(userId);
        order.setCreateDate(creatDate);
        order.setCreateTime(createTime);
        order.setOrderStatus(status);
        order.setOrderPrice(price);
        orderService.save(order);
        return order.getOrderId();


    }

    @Override
    public List<JSONObject> queryUserOrders(BigInteger userId){

        List<JSONObject> jsonObjectList = new ArrayList<>();

        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id",userId);
        List<Order> orderList = orderService.list(orderQueryWrapper);
        for(Order order : orderList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("totalPrice",order.getOrderPrice());
            jsonObject.put("orderId",order.getOrderId());
            jsonObject.put("orderStatus",order.getOrderStatus());
            QueryWrapper<Ticket> ticketQueryWrapper = new QueryWrapper<>();
            ticketQueryWrapper.eq("order_id",order.getOrderId());
            Ticket ticket = ticketService.getOne(ticketQueryWrapper,false);
            String name = stationService.getStationById(ticket.getDepartStation()).getStationName();
            jsonObject.put("deStation",name);
            jsonObject.put("seatType",ticket.getSeatType());
            Train train = trainService.getById(ticket.getTrainId());
            jsonObject.put("trainNum",train.getTrainNumber());
            QueryWrapper<Train_station> train_stationQueryWrapper = new QueryWrapper<>();
            train_stationQueryWrapper.eq("train_id",ticket.getTrainId())
                    .eq("station_id",ticket.getDepartStation());
            Train_station train_station = trainStationService.getOne(train_stationQueryWrapper,false);
            jsonObject.put("deDate",train_station.getEachDepartDate());
            jsonObject.put("deTime",train_station.getEachDepartTime());
            jsonObjectList.add(jsonObject);
        }

        return jsonObjectList;
    }


    @Override
    public boolean cancelOrder(BigInteger orderId){

        Order order = orderService.getById(orderId);
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        List<Ticket> ticketList = ticketService.list(queryWrapper);
        Train_station train_station = trainStationService.getById(ticketList.get(0).getTrainId());


        if(order.getOrderStatus().equals("paid")){
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            java.sql.Time sqlTime = new java.sql.Time(date.getTime());
            if(sqlDate.equals(train_station.getEachDepartDate())){
                if(train_station.getEachDepartTime().getTime()/60/1000-sqlTime.getTime()/60/1000
                <30){
                    return false;
                }
            }else if(sqlDate.after(train_station.getEachDepartDate()))
                return false;
        }

        order.setOrderStatus("cancelled");
        orderService.updateById(order);
        for(Ticket ticket : ticketList) ticket.setTicketStatus("cancelled");
        ticketService.updateBatchById(ticketList);
        return true;
    }

    @Override
    public boolean payOrder(BigInteger orderId){
        Order order = orderService.getById(orderId);
        if(order.getOrderStatus().equals("unpaid")){
            order.setOrderStatus("paid");
            orderService.updateById(order);
        }else return false;
        return true;
    }
}
