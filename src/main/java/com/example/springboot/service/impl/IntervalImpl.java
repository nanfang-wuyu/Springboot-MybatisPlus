package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Info;
import com.example.springboot.entity.Interval;
import com.example.springboot.entity.Station;
import com.example.springboot.entity.Train;
import com.example.springboot.mapper.IntervalMapper;
import com.example.springboot.service.IntervalService;
import com.example.springboot.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class IntervalImpl extends ServiceImpl<IntervalMapper, Interval> implements IntervalService {


    @Autowired
    private IntervalService intervalService;

    @Autowired
    private StationService stationService;

    @Override
    public List<Info> findRestTickets(List<Info> infoList, List<Train> trainList){

        /*for(Info info : infoList){
            for(Train train : trainList){
                if(info.getTrainId().equals(train.getTrainId())){
                    info.setTrainNumber(train.getTrainNumber());
                    info.setTrainName(train.getTrainName());
                }
            }
        }*/
        long[]time = new long[6];
        time[0] = System.currentTimeMillis();

        List<BigInteger> trainIdList = new ArrayList<>();

        Iterator<Info> infoIterator = infoList.iterator();
        while (infoIterator.hasNext()){
            Info info = infoIterator.next();
            Iterator<Train> trainIterator = trainList.iterator();
            boolean match = false;
            while (trainIterator.hasNext()){
                Train train = trainIterator.next();
                if(info.getTrainId().equals(train.getTrainId())){
                    trainIdList.add(info.getTrainId());
                    info.setTrainNumber(train.getTrainNumber());
                    info.setTrainName(train.getTrainName());
                    match = true;
                    break;
                }
            }
            if (!match) infoIterator.remove();
        }


        time[1] = System.currentTimeMillis();



        QueryWrapper<Interval> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("train_id",trainIdList).orderByAsc("interval_id");
        List<Interval> intervalList = intervalService.list(queryWrapper);

        int j = 0;
        for(int i = 0;i<infoList.size();i++){
            Info info = infoList.get(i);
            boolean start = false;
            for(;j<intervalList.size();j++){
                Interval interval = intervalList.get(j);
                if(info.getTrainId().equals(interval.getTrainId())) {
                    if (info.getDeID().equals(interval.getStationDepart())) {
                        start = true;
                        info.setRestHard(interval.getRestHard());
                        info.setRestSoft(interval.getRestSoft());
                        info.setRestSoftSleepDown(interval.getRestSoftSleepDown());
                        info.setRestHardSleepDown(interval.getRestHardSleepDown());
                        info.setRestHardSleepMiddle(interval.getRestHardSleepMiddle());
                        info.setRestSoftSleepUp(interval.getRestSoftSleepUp());
                        info.setRestHardSleepUp(interval.getRestHardSleepUp());
                        info.setRestFirst(interval.getRestFirst());
                        info.setRestSecond(interval.getRestSecond());
                        info.setRestBusiness(interval.getRestBusiness());
                        info.setRestSuper(interval.getRestSuper());

                        if (info.getArID().equals(interval.getStationArrive())) {
                            break;
                        }
                    } else if (start) {
                        info.setRestHard(Math.min(interval.getRestHard(), info.getRestHard()));
                        info.setRestSoft(Math.min(interval.getRestSoft(), info.getRestSoft()));
                        info.setRestSoftSleepDown(Math.min(interval.getRestSoftSleepDown(), info.getRestSoftSleepDown()));
                        info.setRestHardSleepDown(Math.min(interval.getRestHardSleepDown(), info.getRestHardSleepDown()));
                        info.setRestHardSleepMiddle(Math.min(interval.getRestHardSleepMiddle(), info.getRestHardSleepMiddle()));
                        info.setRestSoftSleepUp(Math.min(interval.getRestSoftSleepUp(), info.getRestSoftSleepUp()));
                        info.setRestHardSleepUp(Math.min(interval.getRestHardSleepUp(), info.getRestHardSleepUp()));
                        info.setRestFirst(Math.min(interval.getRestFirst(), info.getRestFirst()));
                        info.setRestSecond(Math.min(interval.getRestSecond(), info.getRestSecond()));
                        info.setRestBusiness(Math.min(interval.getRestBusiness(), info.getRestBusiness()));
                        info.setRestSuper(Math.min(interval.getRestSuper(), info.getRestSuper()));

                        if (info.getArID().equals(interval.getStationArrive())) {
                            break;
                        }
                    }
                }
            }
        }


        /*for(Info info : infoList){
            columnMap.put("train_id",info.getTrainId());
            columnMap.put("station_depart", info.getDeID());

            //info.setTrainDepartStation(stationService.getStationById(info.getDeID()).getStationName());
            //info.setTrainArriveStation(stationService.getStationById(info.getArID()).getStationName());

            //Map<String,Long> restTickets = new HashMap<>();

            while(!columnMap.get("station_depart").equals(info.getArID())) {
                List<Interval> intervalList = intervalService.listByMap(columnMap);
                Interval interval = intervalList.get(0);

                if(info.getDeID().equals(interval.getStationDepart())){
                    info.setRestHard(interval.getRestHard());
                    info.setRestSoft(interval.getRestSoft());
                    info.setRestSoftSleepDown(interval.getRestSoftSleepDown());
                    info.setRestHardSleepDown(interval.getRestHardSleepDown());
                    info.setRestHardSleepMiddle(interval.getRestHardSleepMiddle());
                    info.setRestSoftSleepUp(interval.getRestSoftSleepUp());
                    info.setRestHardSleepUp(interval.getRestHardSleepUp());
                    info.setRestFirst(interval.getRestFirst());
                    info.setRestSecond(interval.getRestSecond());
                    info.setRestBusiness(interval.getRestBusiness());
                    info.setRestSuper(interval.getRestSuper());
                }else {
                    info.setRestHard(Math.min(interval.getRestHard(),info.getRestHard()));
                    info.setRestSoft(Math.min(interval.getRestSoft(),info.getRestSoft()));
                    info.setRestSoftSleepDown(Math.min(interval.getRestSoftSleepDown(),info.getRestSoftSleepDown()));
                    info.setRestHardSleepDown(Math.min(interval.getRestHardSleepDown(),info.getRestHardSleepDown()));
                    info.setRestHardSleepMiddle(Math.min(interval.getRestHardSleepMiddle(),info.getRestHardSleepMiddle()));
                    info.setRestSoftSleepUp(Math.min(interval.getRestSoftSleepUp(),info.getRestSoftSleepUp()));
                    info.setRestHardSleepUp(Math.min(interval.getRestHardSleepUp(),info.getRestHardSleepUp()));
                    info.setRestFirst(Math.min(interval.getRestFirst(),info.getRestFirst()));
                    info.setRestSecond(Math.min(interval.getRestSecond(),info.getRestSecond()));
                    info.setRestBusiness(Math.min(interval.getRestBusiness(),info.getRestBusiness()));
                    info.setRestSuper(Math.min(interval.getRestSuper(),info.getRestSuper()));
                }

                columnMap.put("station_depart", interval.getStationArrive());
            }
        }*/

        time[2] = System.currentTimeMillis();

        for(int i = 0;i<3;i++) System.out.println("time:"+time[i]);

        return infoList;



    }

}
