package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.*;
import com.example.springboot.mapper.TrainMapper;
import com.example.springboot.mapper.TrainStationMapper;
import com.example.springboot.service.RateService;
import com.example.springboot.service.StationService;
import com.example.springboot.service.TrainService;
import com.example.springboot.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrainStationImpl extends ServiceImpl<TrainStationMapper, Train_station> implements TrainStationService {


    @Autowired
    private StationService stationService;

    @Autowired
    private TrainStationService trainStationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private RateService rateService;

    @Override
    public List<Train_station> queryByStationId(Long stationId){

        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("station_id",stationId);
        //...
        List<Train_station> train_stationsList = baseMapper.selectByMap(columnMap);
        return train_stationsList;
    }

    @Override
    public List<Train_station> queryByStationIdAndDate(Long stationId, Date date){
        QueryWrapper<Train_station> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("station_id",stationId);
        queryWrapper.eq("each_depart_date",date);
        List<Train_station> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    public List<Train_station> mergeList(List<Station>stationList, Date date){

        List<Train_station> tsList = new ArrayList<>();
        Long sid;
        for(int i = 0;i< stationList.size();i++){
            Station station = stationList.get(i);
            sid = station.getStationId();
            List<Train_station> list1 = trainStationService.queryByStationIdAndDate(sid,date);
            tsList.addAll(list1);
        }
        return tsList;

    }

    @Override
    public List<Info> queryByAllConditions (String name1, String name2,
                                            Date date, boolean onlyHigh){
        //long[]time = new long[6];
        //time[0] = System.currentTimeMillis();

        List<Station> stationList1 = stationService.getStationByName(name1);
        List<Station> stationList2 = stationService.getStationByName(name2);


        //time[1] = System.currentTimeMillis();

        List<Train_station> tsList1 = mergeList(stationList1,date);
        List<Train_station> tsList2 = mergeList(stationList2,date);

        //time[2] = System.currentTimeMillis();

        Map<Long,String> stationMap = new HashMap<>();
        for(Station station : stationList1) stationMap.put(station.getStationId(),station.getStationName());
        for(Station station : stationList2) stationMap.put(station.getStationId(),station.getStationName());

        //time[3] = System.currentTimeMillis();


        List<Info> listIntersect = new ArrayList<>();
        for(int i = 0;i<tsList1.size();i++){
            Train_station ts1 = tsList1.get(i);
            for(int j = 0;j<tsList2.size();j++){
                Train_station ts2 = tsList2.get(j);
                if(ts1.getTrainId().equals(ts2.getTrainId())){
                    if(ts1.getSequence()<ts2.getSequence()){
                        Info info = new Info();
                        info.setDeID(ts1.getStationId());
                        info.setArID(ts2.getStationId());
                        info.setTrainDepartStation(stationMap.get(ts1.getStationId()));
                        info.setTrainArriveStation(stationMap.get(ts2.getStationId()));
                        info.setTrainId(ts1.getTrainId());
                        info.setTrainDepartDate(ts1.getEachDepartDate());
                        info.setTrainDepartTime(ts1.getEachDepartTime());
                        info.setTrainArriveDate(ts2.getEachArriveDate());
                        info.setTrainArriveTime(ts2.getEachArriveTime());
                        info.setPassDay(info.calculatePassDay(info.getTrainDepartDate(),info.getTrainArriveDate()));
                        listIntersect.add(info);
                    }
                }
            }
        }

        //time[4] = System.currentTimeMillis();

        //for(int i = 0;i<5;i++) System.out.println("time:"+time[i]);

        return listIntersect;

    }


    @Override
    public Price queryAndCalculatePrice(Long SID1, Long SID2, BigInteger trainId){


        QueryWrapper<Train_station> queryWrapper1 = new QueryWrapper<>();

        queryWrapper1.eq("station_id",SID1).eq("train_id",trainId);

        Train_station ts1 = trainStationService.getOne(queryWrapper1,false);

        QueryWrapper<Train_station> queryWrapper2 = new QueryWrapper<>();

        queryWrapper2.eq("station_id",SID2).eq("train_id",trainId);

        Train_station ts2 = trainStationService.getOne(queryWrapper2);

        long journey = ts2.getDistance()-ts1.getDistance();

        return calculatePrice(journey);

    }

    public Price calculatePrice(long journey){

        List<Rate> rateList = rateService.list();
        Rate rate = rateList.get(0);
        Price price = new Price();
        double priceTemp = Math.max(rate.getStartMileage(),journey) * (rate.getRateFloat()+rate.getRateInsurance());





        price.setHard(twoDigits(priceTemp * rate.getRateHard()));
        price.setSoft(twoDigits(priceTemp * rate.getRateSoft()));
        price.setHardSleepDown(twoDigits(priceTemp * rate.getRateHardSleepDown()));
        price.setHardSleepMiddle(twoDigits(priceTemp * rate.getRateHardSleepMiddle()));
        price.setHardSleepUp(twoDigits(priceTemp * rate.getRateHardSleepUp()));
        price.setSoftSleepDown(twoDigits(priceTemp * rate.getRateSoft()));
        price.setSoftSleepUp(twoDigits(priceTemp * rate.getRateSoftSleepUp()));
        price.setFirst(twoDigits(priceTemp * rate.getRateFirst()));
        price.setSecond(twoDigits(priceTemp * rate.getRateSecond()));
        price.setBusiness(twoDigits(priceTemp * rate.getRateBusiness()));
        price.setSuper(twoDigits(priceTemp * rate.getRateSuper()));
        return price;

        //保留两位小数

    }

    public double twoDigits(double d){
        BigDecimal b = new BigDecimal(d);
        return b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public List<Train_station> queryByTrainId(BigInteger trainId){


        QueryWrapper <Train_station> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("train_id",trainId).orderByAsc("sequence");
        //...
        List<Train_station> train_stationsList = trainStationService.list(queryWrapper);
        return train_stationsList;

    }


}
