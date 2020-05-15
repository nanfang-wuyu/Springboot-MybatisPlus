package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

@Data
@TableName("train")
public class Train {

    @TableId("train_id")
    private BigInteger trainId;

    @TableField("train_number")
    private String trainNumber;

    @TableField("train_name")
    private String trainName;

    @TableField("train_type")
    private String trainType;

    @TableField("train_depart_station")
    private String trainDepartStation;

    @TableField("train_arrive_station")
    private String trainArriveStation;

    @TableField("train_depart_date")
    private Date trainDepartDate;

    @TableField("train_arrive_date")
    private Date trainArriveDate;

    @TableField("train_depart_time")
    private Time trainDepartTime;

    @TableField("train_arrive_time")
    private Time trainArriveTime;



}
