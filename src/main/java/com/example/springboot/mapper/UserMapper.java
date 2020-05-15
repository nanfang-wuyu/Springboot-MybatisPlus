package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Mapper
@Component (value = "userMapper")
public interface UserMapper extends BaseMapper<User> {

    User getUserById(@Param("id") long id);

    void addUser(String name, BigInteger phone, BigInteger card);

}
