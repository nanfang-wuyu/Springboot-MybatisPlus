package com.example.springboot.controller;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.entity.Station;
import com.example.springboot.entity.Train;
import com.example.springboot.entity.Train_station;
import com.example.springboot.service.StationService;
import com.example.springboot.service.TrainService;
import com.example.springboot.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;

@RestController
public class TrainStationController {

    @Autowired
    private TrainStationService trainStationService;
    @Autowired
    private StationService stationService;
    @Autowired
    private TrainService trainService;

    @GetMapping("/queryBySIDInTrainStation/{SID}")
    public List<Train_station> queryByStationId (@PathVariable Long SID){
        List<Train_station> list = trainStationService.queryByStationId(SID);
        return list;
    }

    @GetMapping("/getStationsAndTime/{train_number}")
    public List<JSONObject> getStationsAndTime(@PathVariable String train_number){

        BigInteger train_id = trainService.queryByNum(train_number);
        if(train_id==null) return null;
        List<Train_station> train_stationList = trainStationService.queryByTrainId(train_id);
        List<Long> stationIds = new ArrayList<>();
        for(Train_station train_station : train_stationList)
            stationIds.add(train_station.getStationId());
        List<Station> stationList = stationService.listByIds(stationIds);


            List<JSONObject> jsonObjectList = new ArrayList<>();

        try {
            for (int i = 0; i < train_stationList.size(); i++) {

                JSONObject jsonObject = new JSONObject();
                Train_station train_station = train_stationList.get(i);

                Time deTime = train_station.getEachDepartTime();
                Time arTime = train_station.getEachArriveTime();

                long subTime;
                if(deTime == null || arTime == null) subTime = 0;
                else subTime = (deTime.getTime() - arTime.getTime())/60/1000;

                Iterator<Station> stationIterator = stationList.iterator();
                while(stationIterator.hasNext()){
                    Station station = stationIterator.next();
                    if(station.getStationId().equals(train_station.getStationId())){
                        jsonObject.put("station", station.getStationName());
                        stationIterator.remove();
                        break;
                    }
                }


                jsonObject.put("deTime", deTime);
                jsonObject.put("arTime", arTime);
                jsonObject.put("intervalTime", subTime);
                jsonObjectList.add(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObjectList;
    }


}
