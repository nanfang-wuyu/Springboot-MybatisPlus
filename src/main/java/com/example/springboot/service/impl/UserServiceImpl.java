package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /*@Autowired
    private UserMapper userMapper;*/

    @Override
    public User getUserById(long id){
        return baseMapper.selectById(id);
    }

    @Override
    public void addUser(String name, BigInteger phone, BigInteger card){
        User user = new User();
        user.setUsername(name);
        user.setCardId(card);
        user.setPhone(phone);
        baseMapper.insert(user);
    }




}
