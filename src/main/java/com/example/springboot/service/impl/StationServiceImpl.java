package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Station;
import com.example.springboot.entity.Train;
import com.example.springboot.mapper.StationMapper;
import com.example.springboot.service.StationService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {


    @Override
    public Station getStationById(Long id){
        return baseMapper.selectById(id);
    }

    @Override
    public List<Station> getStationByName(String name){
        QueryWrapper<Station> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("station_name",name);
        List<Station> stationList = baseMapper.selectList(queryWrapper);
        return stationList;
    }


}
