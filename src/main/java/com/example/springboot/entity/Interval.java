package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;


@Data
public class Interval {

    @TableId(value = "interval_id", type = IdType.AUTO)
    private BigInteger intervalId;

    @TableField("train_id")
    private BigInteger trainId;

    @TableField("station_depart")
    private Long stationDepart;

    @TableField("station_arrive")
    private Long stationArrive;

    @TableField("rest_hard")
    private Long restHard;

    @TableField("rest_soft")
    private Long restSoft;

    @TableField("rest_business")
    private Long restBusiness;

    @TableField("rest_hard_sleep_down")
    private Long restHardSleepDown;

    @TableField("rest_hard_sleep_middle")
    private Long restHardSleepMiddle;

    @TableField("rest_hard_sleep_up")
    private Long restHardSleepUp;

    @TableField("rest_soft_sleep_down")
    private Long restSoftSleepDown;

    @TableField("rest_soft_sleep_up")
    private Long restSoftSleepUp;

    @TableField("rest_first")
    private Long restFirst;

    @TableField("rest_second")
    private Long restSecond;

    @TableField("rest_super")
    private Long restSuper;


}
