package com.example.springboot.service;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Info;
import com.example.springboot.entity.Train;
import com.example.springboot.entity.Train_station;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Collection;
import java.util.List;


@Component(value = "trainService")
public interface TrainService extends IService<Train> {


    /**
     * 通过出到车站 日期 列车类型 搜索经过列车num 确定列车id
     *
     * 按出发时间排序
     *
     * ->返回train row
     */
    /*@Select("select * from train where id = #{train_id}")
    @Results({
            @Result(property="Train_station",column="train_id",
                    many=@Many(select="com.example.springboot.service.TrainStationServiceImpl.queryByAllConditions"))
    })
    List<Train> queryByTS(Train_station ts);*/

    List<Train> queryByTS(List<Info> infoList, boolean onlyHigh);
}
