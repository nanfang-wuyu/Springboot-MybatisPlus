package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Order;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /*@Autowired
    private UserMapper userMapper;*/

    @Autowired
    private UserService userService;

    @Override
    public User getUserById(long id){
        return baseMapper.selectById(id);
    }

    /*@Override
    public void addUser(String name, BigInteger phone, BigInteger card){
        User user = new User();
        user.setUsername(name);
        user.setCardId(card);
        user.setPhone(phone);
        baseMapper.insert(user);
    }*/

    @Override
    public void addUser(String name, String openId){
        User user = new User();
        user.setUsername(name);
        user.setOpenid(openId);
        userService.save(user);
    }


    @Override
    public User getUserByOpenId(String openId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openId);
        List<User> userList = userService.list(queryWrapper);
        if(userList.size()>0) return userList.get(0);
        return null;
    }





    @Override
    public BigInteger login(String name, String url){

        JSONObject jsonObject;
        String result = "";
        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);

            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Expires", "0");

            connection.connect();

            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String readline;
            while ((readline = in.readLine()) != null) {
                result += readline;
            }

            jsonObject = JSON.parseObject(result);
            String openId = jsonObject.getString("openid");

            User user = userService.getUserByOpenId(openId);
            if(user == null) {
                userService.addUser(name, openId);
                return userService.getUserByOpenId(openId).getUserId();
            } else return user.getUserId();


        } catch (Exception e1) {
            System.out.println("Wrong Getting!");
            e1.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }







}
