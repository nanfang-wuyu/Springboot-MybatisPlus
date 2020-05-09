package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigInteger;

@Data
@TableName("user")
public class User {

    @TableId("user_id")
    private BigInteger user_id;

    private String username;

    @TableField("phone_number")
    private BigInteger phone;

    private BigInteger card_id;
}
