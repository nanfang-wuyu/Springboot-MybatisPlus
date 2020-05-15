package com.example.springboot.controller;


import com.example.springboot.entity.Station;
import com.example.springboot.entity.Train;
import com.example.springboot.entity.Train_station;
import com.example.springboot.service.StationService;
import com.example.springboot.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TrainStationController {

    @Autowired
    private TrainStationService trainStationService;
    @Autowired
    private StationService stationService;

    @GetMapping("/queryBySIDInTrainStation/{SID}")
    public List<Train_station> queryByStationId (@PathVariable Long SID){
        List<Train_station> list = trainStationService.queryByStationId(SID);
        return list;
    }

}
