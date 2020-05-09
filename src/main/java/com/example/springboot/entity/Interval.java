package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;


public class Interval {

    @TableId("interval_id")
    private BigInteger interval_id;

    private BigInteger train_id;

    private Long station_depart;

    private Long station_arrive;

    private Long rest_hard;

    private Long rest_soft;

    private Long rest_business;

    private Long rest_hard_sleep_down;

    private Long rest_hard_sleep_middle;

    private Long rest_hard_sleep_up;

    private Long rest_soft_sleep_down;

    private Long rest_soft_sleep_up;

    private Long rest_first;

    private Long rest_second;

    private Long rest_super;


}
