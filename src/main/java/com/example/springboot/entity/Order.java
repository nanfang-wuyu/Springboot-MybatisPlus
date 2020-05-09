package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.sql.Date;
import java.sql.Time;


@Data
public class Order {

    @TableId("order_id")
    private Long order_id;

    private Long user_id;

    private Date create_date;

    private Time create_time;

    private String order_status;

    private Double order_price;



}
