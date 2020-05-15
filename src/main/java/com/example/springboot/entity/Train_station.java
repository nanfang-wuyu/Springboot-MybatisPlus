package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

@Data
public class Train_station {

    @TableField("train_id")
    private BigInteger trainId;

    @TableField("station_id")
    private Long stationId;

    @TableField("each_depart_date")
    private Date eachDepartDate;

    @TableField("each_arrive_date")
    private Date eachArriveDate;

    @TableField("each_depart_time")
    private Time eachDepartTime;

    @TableField("each_arrive_time")
    private Time eachArriveTime;

    @TableField("distance")
    private Long distance;

    @TableField("sequence")
    private Long sequence;




}
