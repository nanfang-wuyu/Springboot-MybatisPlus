package com.example.springboot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.*;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@Component(value = "trainStationService")
public interface TrainStationService extends IService<Train_station> {

    /**
     *
     */
    List<Train_station> queryByStationId(Long stationId);

    List<Train_station> queryByStationIdAndDate(Long stationId, Date date);

    List<Info> queryByAllConditions(String name1, String name2,
                                    Date date, boolean onlyHigh);


    Price queryAndCalculatePrice(Long SID1, Long SID2, BigInteger trainId);
}
