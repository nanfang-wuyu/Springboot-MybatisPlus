package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Recommend;
import com.example.springboot.mapper.RecommendMapper;
import com.example.springboot.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    @Autowired
    private RecommendService recommendService;

    @Override
    public List<Recommend> getRecommend(){

        return recommendService.list();

    }

}
