package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Info;
import com.example.springboot.entity.Interval;
import com.example.springboot.entity.Station;
import com.example.springboot.entity.Train;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component(value = "intervalService")
public interface IntervalService extends IService<Interval> {


    List<Info>  findRestTickets(List<Info> infoList,List<Train> trainList);

    boolean ensureRestTickets(String seatType, BigInteger trainId, Long deID, Long arID, int num);

}
