package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.sql.Date;
import java.sql.Time;


@Data
public class Order {

    @TableId("order_id")
    private Long orderId;

    @TableField("user_id")
    private Long userId;

    @TableField("create_date")
    private Date createDate;

    @TableField("create_time")
    private Time createTime;

    @TableField("order_status")
    private String orderStatus;

    @TableField("order_price")
    private Double orderPrice;



}
