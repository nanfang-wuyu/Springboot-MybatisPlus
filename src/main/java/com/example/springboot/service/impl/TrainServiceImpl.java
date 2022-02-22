package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.*;
import com.example.springboot.mapper.StationMapper;
import com.example.springboot.mapper.TrainMapper;
import com.example.springboot.service.IntervalService;
import com.example.springboot.service.StationService;
import com.example.springboot.service.TrainService;
import com.example.springboot.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TrainServiceImpl extends ServiceImpl<TrainMapper, Train> implements TrainService {


    @Autowired
    private TrainStationService trainStationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private IntervalService intervalService;


    @Override
    public List<Train> queryByTS(List<Info> infoList, boolean onlyHigh){

        System.out.println("size:"+infoList.size());
        List<BigInteger>list = new ArrayList<>();

        for(Info info : infoList) list.add(info.getTrainId());

        QueryWrapper<Train> queryWrapper = new QueryWrapper<>();
        List<Train> list1 = new ArrayList<>();

        System.out.println(onlyHigh);
        if(onlyHigh) {
            queryWrapper.in("train_id", list).
                    like("train_type", "动车").or().
                    like("train_type", "高速列车").or().
                    like("train_type", "城际动车");

            list1 = baseMapper.selectList(queryWrapper);
        }else {
            list1 = baseMapper.selectBatchIds(list);
        }
        /*list1.sort((o1, o2) -> {
            if(o1.getTrainDepartDate().before(o2.getTrainDepartDate())){
                return -1;
            }else if(o1.getTrainDepartDate().after(o2.getTrainDepartDate())){
                return 1;
            }else {
                if(o1.getTrainDepartTime().before(o2.getTrainDepartTime())){
                    return -1;
                }else if(o1.getTrainDepartTime().after(o2.getTrainDepartTime())){
                    return 1;
                }else return 0;
            }
        });*/
        return list1;
    }

    @Override
    public BigInteger queryByNum(String trainNum){

        QueryWrapper<Train> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("train_number",trainNum);
        List<Train> trainList = trainService.list(queryWrapper);
        if(trainList.size()==0) return null;
        else return trainList.get(0).getTrainId();

    }




    @Override
    public void copyData(){
        /*List<Train> baseList = trainService.list();
        long cnt = baseList.size();
        List<Train> trainList = new ArrayList<>();
        for(int i = 1;i<30;i++){

            for(Train train : baseList){
                Train newTrain = new Train();
                newTrain.setTrainName(train.getTrainName());
                newTrain.setTrainNumber(train.getTrainNumber());
                newTrain.setTrainType(train.getTrainType());
                newTrain.setTrainDepartStation(train.getTrainDepartStation());
                newTrain.setTrainArriveStation(train.getTrainArriveStation());
                newTrain.setTrainDepartDate(Date.valueOf
                        (train.getTrainDepartDate().toLocalDate().plusDays(i)));
                newTrain.setTrainArriveDate(Date.valueOf
                        (train.getTrainArriveDate().toLocalDate().plusDays(i)));
                newTrain.setTrainDepartTime(train.getTrainDepartTime());
                newTrain.setTrainArriveTime(train.getTrainArriveTime());
                trainList.add(newTrain);
            }
        }
        trainService.saveBatch(trainList);*/

        long cnt = 10070L;

        /*List<Interval> intervalList = new ArrayList<>();
        for(int i = 1;i<30;i++){
            List<Interval> baseIntervalList = intervalService.list();
            for(Interval interval : baseIntervalList){
                Interval newInterval = new Interval();
                newInterval.setTrainId(interval.getTrainId()
                        .add(BigInteger.valueOf(i * cnt)));
                newInterval.setStationDepart(interval.getStationDepart());
                newInterval.setStationArrive(interval.getStationArrive());
                newInterval.setRestHard(interval.getRestHard());
                newInterval.setRestSoft(interval.getRestSoft());
                newInterval.setRestHardSleepDown(interval.getRestHardSleepDown());
                newInterval.setRestHardSleepMiddle(interval.getRestHardSleepMiddle());
                newInterval.setRestHardSleepUp(interval.getRestHardSleepUp());
                newInterval.setRestSoftSleepDown(interval.getRestSoftSleepDown());
                newInterval.setRestSoftSleepUp(interval.getRestSoftSleepUp());
                newInterval.setRestBusiness(interval.getRestBusiness());
                newInterval.setRestFirst(interval.getRestFirst());
                newInterval.setRestSecond(interval.getRestSecond());
                newInterval.setRestSuper(interval.getRestSuper());
                intervalList.add(newInterval);
            }
            intervalService.saveBatch(intervalList);
        }*/



        List<Train_station> baseTrainStationList = trainStationService.list();

        for(int i = 11;i<12;i++){
            List<Train_station> trainStationList = new ArrayList<>();
            for(Train_station trainStation : baseTrainStationList){
                Train_station newTrainStation = new Train_station();
                newTrainStation.setTrainId(trainStation.getTrainId()
                        .add(BigInteger.valueOf(i * cnt)));
                newTrainStation.setStationId(trainStation.getStationId());
                newTrainStation.setDistance(trainStation.getDistance());
                newTrainStation.setSequence(trainStation.getSequence());
                newTrainStation.setEachDepartTime(trainStation.getEachDepartTime());
                newTrainStation.setEachArriveTime(trainStation.getEachArriveTime());
                newTrainStation.setEachDepartDate(Date.valueOf
                        (trainStation.getEachDepartDate().toLocalDate().plusDays(i)));
                newTrainStation.setEachArriveDate(Date.valueOf
                        (trainStation.getEachArriveDate().toLocalDate().plusDays(i)));

                trainStationList.add(newTrainStation);

            }
            trainStationService.saveBatch(trainStationList);
        }








    }


}
