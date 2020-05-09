package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class Rate {

    private Double start_mileage;

    private Double rate_hard;

    private Double rate_soft;

    private Double rate_business;

    private Double rate_hard_sleep_down;

    private Double rate_hard_sleep_middle;

    private Double rate_hard_sleep_up;

    private Double rate_soft_sleep_down;

    private Double rate_soft_sleep_up;

    private Double rate_first;

    private Double rate_second;

    private Double rate_super;

    private Double rate_float;

    private Double rate_insurance;

    private Double rate_student;

    @TableField(exist = false)
    private Double final_price;

}
