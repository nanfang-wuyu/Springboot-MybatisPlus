package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("station")
public class Station {

    @TableId("station_id")
    private Long stationId;

    @TableField("station_name")
    private String stationName;

    @TableField("station_pinyin_headerchar")
    private String pinyinHeader;

    @TableField("station_pinyin")
    private String pinyin;

}
