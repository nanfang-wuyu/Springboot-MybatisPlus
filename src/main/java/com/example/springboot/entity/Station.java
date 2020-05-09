package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data

public class Station {

    @TableId("station_id")
    private Long station_id;

    private String station_name;

    @TableField("station_pinyin_headerchar")
    private String pinyin_header;

    @TableField("station_pinyin")
    private String pinyin;

}
