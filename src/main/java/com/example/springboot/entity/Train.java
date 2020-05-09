package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
public class Train {

    @TableId("train_id")
    private BigInteger train_id;

    private String train_number;

    private String train_name;

    private String train_type;

    private String train_depart_station;

    private String train_arrive_station;

    private Date train_depart_date;

    private Date train_arrive_date;

    private Time train_depart_time;

    private Time train_arrive_time;



}
