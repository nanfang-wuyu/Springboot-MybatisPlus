package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigInteger;

@Data
@TableName("users")
public class User {

    @TableId(value = "user_id",type = IdType.AUTO)
    private BigInteger userId;

    @TableField("username")
    private String username;

    /*@TableField("phone_number")
    private BigInteger phone;

    @TableField("card_id")
    private BigInteger cardId;*/

    @TableField("openid")
    private String openid;
}
