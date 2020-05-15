package com.example.springboot.controller;


import com.example.springboot.entity.Info;
import com.example.springboot.entity.Interval;
import com.example.springboot.entity.Price;
import com.example.springboot.entity.Train;
import com.example.springboot.service.IntervalService;
import com.example.springboot.service.TrainService;
import com.example.springboot.service.TrainStationService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@RestController
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainStationService trainStationService;

    @Autowired
    private IntervalService intervalService;

    @GetMapping("/queryByAllConditions/{name1,name2,date,type}")
    public List<Info> queryByAllConditions (@PathVariable String name1, String name2,
                                             Date date, String type){
        if(name1.equals(name2)) return null;
        List<Info> infoList = trainStationService.queryByAllConditions(name1,name2,date,type);
        List<Train> trainList = trainService.queryByTS(infoList);
        List<Info> finalInfoList = intervalService.findRestTickets(infoList,trainList);

        return finalInfoList;
    }

    @GetMapping("/queryPrice/{SID1,SID2,type,trainId}")
    public Price queryPrice(@PathVariable Long SID1, Long SID2, String type, BigInteger trainId){

        Price price = trainStationService.queryAndCalculatePrice(SID1,SID2,type,trainId);
        return price;

    }



}
