package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Rate;
import com.example.springboot.entity.Station;
import com.example.springboot.mapper.RateMapper;
import com.example.springboot.mapper.StationMapper;
import com.example.springboot.service.RateService;
import com.example.springboot.service.StationService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RateServiceImpl extends ServiceImpl<RateMapper, Rate> implements RateService {



}
