package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class Rate {

    //@TableId
    @TableField("start_mileage")
    private Double startMileage;

    @TableField("rate_hard")
    private Double rateHard;

    @TableField("rate_soft")
    private Double rateSoft;

    @TableField("rate_business")
    private Double rateBusiness;

    @TableField("rate_hard_sleep_down")
    private Double rateHardSleepDown;

    @TableField("rate_hard_sleep_middle")
    private Double rateHardSleepMiddle;

    @TableField("rate_hard_sleep_up")
    private Double rateHardSleepUp;

    @TableField("rate_soft_sleep_down")
    private Double rateSoftSleepDown;

    @TableField("rate_soft_sleep_up")
    private Double rateSoftSleepUp;

    @TableField("rate_first")
    private Double rateFirst;

    @TableField("rate_second")
    private Double rateSecond;

    @TableField("rate_super")
    private Double rateSuper;

    @TableField("rate_float")
    private Double rateFloat;

    @TableField("rate_insurance")
    private Double rateInsurance;

    @TableField("rate_student")
    private Double rateStudent;

    @TableField(exist = false)
    private Double finalPrice;

}
