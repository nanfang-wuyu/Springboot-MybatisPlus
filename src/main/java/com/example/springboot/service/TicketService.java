package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Order;
import com.example.springboot.entity.Ticket;
import org.springframework.stereotype.Component;

@Component(value = "ticketService")
public interface TicketService extends IService<Ticket> {



}