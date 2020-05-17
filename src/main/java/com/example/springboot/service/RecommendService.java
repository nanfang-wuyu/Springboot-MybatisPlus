package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Recommend;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "recommendService")
public interface RecommendService extends IService<Recommend> {


    List<Recommend> getRecommend();

}
