package com.example.springboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "recommendMapper")
public interface RecommendMapper extends BaseMapper<Recommend> {
}
