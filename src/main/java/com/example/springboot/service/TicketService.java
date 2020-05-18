package com.example.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Info;
import com.example.springboot.entity.Order;
import com.example.springboot.entity.Passenger;
import com.example.springboot.entity.Ticket;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Component(value = "ticketService")
public interface TicketService extends IService<Ticket> {

/*
    boolean ensureNoConflict(List<Passenger> passengerList, Date deDate, Date arDate,
                             Time deTime, Time arTime);*/

    void addTicket(List<Passenger> passengerList, BigInteger userId, String seatType,
                   Info trainInfo, boolean isPaid, double price);

    JSONObject queryOrderTickets(BigInteger orderId);
}