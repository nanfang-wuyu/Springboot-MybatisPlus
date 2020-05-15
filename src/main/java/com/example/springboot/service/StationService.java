package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Station;
import com.example.springboot.entity.Train;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.Date;


@Component (value = "stationService")
public interface StationService extends IService<Station> {

    Station getStationById(Long id);

    List<Station> getStationByName(String name);

}
