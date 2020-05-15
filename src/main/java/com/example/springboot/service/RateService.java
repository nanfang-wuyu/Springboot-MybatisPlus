package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Rate;
import com.example.springboot.entity.Station;
import org.springframework.stereotype.Component;

import java.util.List;


@Component (value = "rateService")
public interface RateService extends IService<Rate> {


}
