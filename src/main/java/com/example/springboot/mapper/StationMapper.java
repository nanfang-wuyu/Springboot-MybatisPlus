package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Station;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component (value = "stationMapper")

public interface StationMapper extends BaseMapper<Station> {

    Station getStationById(@Param("id") Long id);

    Station getStationByName(@Param("name") String name);

}
