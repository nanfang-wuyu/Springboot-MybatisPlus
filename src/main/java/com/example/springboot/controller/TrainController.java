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

    @GetMapping("/queryByAllConditions/{name1}/{name2}/{date}/{onlyHigh}")
    public List<Info> queryByAllConditions (@PathVariable String name1, @PathVariable String name2,
                                             @PathVariable Date date, @PathVariable boolean onlyHigh){
        if(name1.equals(name2)) return null;
        long[] time = new long[4];
        time[0] = System.currentTimeMillis();
        List<Info> infoList = trainStationService.queryByAllConditions(name1,name2,date,onlyHigh);
        time[1] = System.currentTimeMillis();
        if(infoList.size()==0) return null;
        List<Train> trainList = trainService.queryByTS(infoList, onlyHigh);
        time[2] = System.currentTimeMillis();
        List<Info> finalInfoList = intervalService.findRestTickets(infoList,trainList);
        time[3] = System.currentTimeMillis();
        for(int i = 0;i<4;i++) System.out.println(time[i]);

        return finalInfoList;
    }

    @GetMapping("/queryPrice/{SID1}/{SID2}/{trainId}")
    public Price queryPrice(@PathVariable Long SID1, @PathVariable Long SID2, @PathVariable BigInteger trainId){

        Price price = trainStationService.queryAndCalculatePrice(SID1,SID2,trainId);
        return price;

    }



}
