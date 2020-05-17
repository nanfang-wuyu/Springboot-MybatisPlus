package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("recommend")
public class Recommend {

    @TableField("name")
    String name;

    @TableField("is5a")
    Boolean is5A;

    @TableField("city")
    String city;

}
