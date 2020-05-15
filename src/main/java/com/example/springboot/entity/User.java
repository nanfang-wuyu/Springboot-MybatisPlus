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
    private BigInteger userId;

    @TableField
    private String username;

    @TableField("phone_number")
    private BigInteger phone;

    @TableField("card_id")
    private BigInteger cardId;
}
