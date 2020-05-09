package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;


public class Train_station {

    private BigInteger train_id;

    private Long station_id;

    private Date each_depart_date;

    private Date each_arrive_date;

    private Time each_depart_time;

    private Time each_arrive_time;

    private Long distance;

    private Long sequence;




}
