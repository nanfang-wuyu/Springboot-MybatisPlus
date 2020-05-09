package com.example.springboot;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.entity.Station;
import com.example.springboot.mapper.StationMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest

public class RetrieveTest {
    @Autowired
    private StationMapper stationMapper;

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

}
