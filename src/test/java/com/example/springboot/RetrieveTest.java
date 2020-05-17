package com.example.springboot;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.entity.*;
import com.example.springboot.mapper.StationMapper;
import com.example.springboot.mapper.TrainMapper;
import com.example.springboot.service.IntervalService;
import com.example.springboot.service.StationService;
import com.example.springboot.service.TrainService;
import com.example.springboot.service.TrainStationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.sql.Date;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class RetrieveTest {
    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private TrainMapper trainMapper;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainStationService trainStationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private IntervalService intervalService;

    @Test
    public void testSelect() {
        Station station = stationMapper.selectById(1);
        System.out.println(station);
    }

    @Test
    public void selectByIds(){
        List<Long>idList = Arrays.asList(1L,2L,3L);
        List<Station> stationList = stationMapper.selectBatchIds(idList);
        stationList.forEach(System.out::println);
    }

    @Test
    public void selectByMap(){
        Map<String,Object>columnMap = new HashMap<>();
        columnMap.put("station_name","明水河");
        //...
        List<Station> stationList = stationMapper.selectByMap(columnMap);
        stationList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper(){
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("station_name","水");//包含字符
        queryWrapper.lt("station_id",10);//less than
        queryWrapper.between("station_id",1,10);//between
        queryWrapper.isNotNull("station_pinyin");//not null

        //queryWrapper.likeRight("station_name","明").or().ge("station_id",7)
        //.orderByDesc("station_pinyin").orderByAsc("station_id");
        //首字符, or, greater than, 降序， 升序


        List<Station> stationList = stationMapper.selectList(queryWrapper);
        stationList.forEach(System.out::println);
    }

    @Test
    public void getStationByName(){
    }

    @Test
    public void getStationByNamePlus(){
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("station_name","北京");
        List<Station> stationList = stationMapper.selectList(queryWrapper);
        stationList.forEach(System.out::println);
    }

    @Test
    public void queryByTS(){
        List<Train> list1 =  trainMapper.selectBatchIds(queryByAllConditions());
        list1.sort((o1, o2) -> {
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
        });
        list1.forEach(System.out::println);
    }


    public List<BigInteger> queryByAllConditions (){
        String name1 = "北京西";
        String name2 = "广州南";
        Date date = Date.valueOf("2020-05-10");
        String type = "动车";
        List<Station> stationList1 = stationService.getStationByName(name1);
        List<Station> stationList2 = stationService.getStationByName(name2);

        List<Train_station> tsList1 = mergeList(stationList1,date);
        List<Train_station> tsList2 = mergeList(stationList2,date);

        List<BigInteger> listIntersect = new ArrayList<>();
        for(int i = 0;i<tsList1.size();i++){
            Train_station ts1 = tsList1.get(i);
            for(int j = 0;j<tsList2.size();j++){
                Train_station ts2 = tsList2.get(j);
                if(ts1.getTrainId().equals(ts2.getTrainId())){
                    if(ts1.getSequence()<ts2.getSequence()){
                        listIntersect.add(ts1.getTrainId());
                    }
                }
            }
        }
        //listIntersect.forEach(System.out::println);
        return listIntersect;
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



    @Test
    public void queryInfo(){
        String name1 = "北京西";
        String name2 = "广州南";
        Date date = Date.valueOf("2020-05-10");
        boolean onlyHigh = true;
        List<Info> infoList = queryByAllConditionsPlus(name1,name2,date,onlyHigh);
        infoList.forEach(System.out::println);
        Info info = infoList.get(0);
        Price price = queryPrice(info.getDeID(),info.getArID(),info.getTrainId());
        System.out.println(price);
    }


    public List<Info> queryByAllConditionsPlus (String name1, String name2,
                                            Date date, boolean onlyHigh){
        if(name1.equals(name2)) return null;
        List<Info> infoList = trainStationService.queryByAllConditions(name1,name2,date,onlyHigh);
        List<Train> trainList = trainService.queryByTS(infoList,onlyHigh);

        List<Info> finalInfoList = intervalService.findRestTickets(infoList,trainList);

        return finalInfoList;
    }


    public Price queryPrice(Long SID1, Long SID2, BigInteger trainId){

        Price price = trainStationService.queryAndCalculatePrice(SID1,SID2, trainId);
        return price;

    }

}
