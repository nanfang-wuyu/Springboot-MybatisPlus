package com.example.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component (value = "userService")
public interface UserService extends IService<User> {


    public User getUserById(long id);
    /**
     *增加用户数据
     **/
    public void addUser(String name, String openId);
    /**
     *用户登入
     **/
    /**
     *用户登出
     **/
    /**
     * 判断是否管理员并返回信息
     */
    /**
     * 增、删、修改管理员数据（隔离）
     * @return
     */

    BigInteger login(String name, String url);

    User getUserByOpenId(String openId);

}
