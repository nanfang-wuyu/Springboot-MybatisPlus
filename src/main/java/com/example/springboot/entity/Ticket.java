package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

@Data
public class Ticket {

    @TableId(value = "ticket_id", type = IdType.AUTO)
    private BigInteger ticketId;

    @TableField("order_id")
    private BigInteger orderId;

    @TableField("train_id")
    private BigInteger trainId;

    @TableField("ticket_price")
    private Double ticketPrice;

    @TableField("depart_station")
    private Long departStation;

    @TableField("arrive_station")
    private Long arriveStation;

    @TableField("ticket_status")
    private String ticketStatus;

    @TableField("passenger_id")
    private String passengerId;

    @TableField("passenger_name")
    private String passengerName;

    @TableField("ticket_type")
    private String ticketType;

    @TableField("seat_type")
    private String seatType;

}
