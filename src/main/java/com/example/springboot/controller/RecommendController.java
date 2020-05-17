package com.example.springboot.controller;

import com.example.springboot.entity.Recommend;
import com.example.springboot.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/getRecommend")
    public List<Recommend> getRecommend(){
        return recommendService.getRecommend();
    }

}
