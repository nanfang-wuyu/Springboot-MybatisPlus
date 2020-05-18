package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;


@Data
@TableName("orders")
public class Order {

    @TableId(value = "order_id", type = IdType.AUTO)
    private BigInteger orderId;

    @TableField("user_id")
    private BigInteger userId;

    @TableField("create_date")
    private Date createDate;

    @TableField("create_time")
    private Time createTime;

    @TableField("order_status")
    private String orderStatus;

    @TableField("order_price")
    private Double orderPrice;



}
