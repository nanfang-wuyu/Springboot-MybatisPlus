package com.example.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.springboot.entity.Info;
import com.example.springboot.entity.Passenger;
import com.example.springboot.entity.Train;
import com.example.springboot.service.IntervalService;
import com.example.springboot.service.OrderService;
import com.example.springboot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketController {
/*
    @Autowired
    private TicketService ticketService;*/

    /*@GetMapping(/queryForOrders)
    public*/

    @Autowired
    private IntervalService intervalService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/makeTickets/{passengerList}/{userId}/{seatType}/{trainInfo}/{isPaid}/{price}")
    public int makeTickets(@PathVariable List<Passenger> passengerList,
                           @PathVariable BigInteger userId,
                           @PathVariable String seatType,
                           @PathVariable Info trainInfo,
                           @PathVariable boolean isPaid,
                           @PathVariable double price){


        boolean enough = intervalService.ensureRestTickets(seatType,trainInfo.getTrainId(),
                trainInfo.getDeID(),trainInfo.getArID(),passengerList.size());
        if(!enough) return 2;

        else {
            ticketService.addTicket(passengerList, userId, seatType, trainInfo, isPaid, price);
            return 1;
        }

    }

    @GetMapping("/queryOrderTickets/{orderId}")
    public JSONObject queryOrderTickets(@PathVariable BigInteger orderId){
        return ticketService.queryOrderTickets(orderId);
    }

}
