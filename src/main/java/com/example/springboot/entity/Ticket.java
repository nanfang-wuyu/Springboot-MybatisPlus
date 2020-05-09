package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;


public class Ticket {

    @TableId("ticket_id")
    private BigInteger ticket_id;

    private BigInteger order_id;

    private BigInteger train_id;

    private Double ticket_price;

    private Double ticket_entrance;

    private Long depart_station;

    private Long arrive_station;

    private String ticket_status;

    private BigInteger passenger_id;

    private String passenger_name;

    private String ticket_type;



}
