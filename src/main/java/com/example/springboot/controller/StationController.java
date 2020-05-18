package com.example.springboot.controller;

import com.example.springboot.entity.Station;
import com.example.springboot.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/getStationById/{id}")
    public Station getStationById(@PathVariable Long id){
        Station station = stationService.getStationById(id);
        System.out.println(station);
        return station;
    }

    @GetMapping("/getStationByName/{name}")
    public List<String> getStationByName(@PathVariable String name){
        List<Station> stationList = stationService.getStationByName(name);
        List<String> stringList = new ArrayList<>();
        for(Station station : stationList) stringList.add(station.getStationName());
        return stringList;
    }



}
