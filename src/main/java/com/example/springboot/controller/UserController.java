package com.example.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.entity.Station;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*@GetMapping("/addUser/{name,phone,card}")
    public void addUser(@PathVariable String name, BigInteger phone, BigInteger card){
        userService.addUser(name, phone, card);
    }*/

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User helloUser() {
        User user = userService.getUserById(1);
        System.out.println(user);
        return user;
    }

    @GetMapping("/login/{name}/{code}")
    public BigInteger login(@PathVariable String name, @PathVariable String code){

            String apiStr = "https://api.weixin.qq.com/sns/" +
                    "jscode2session?appid=wxbd4a2da637158ad8&secret=" +
                    "c434f38937785733e25a5edf2cadce51&js_code=" +
                    code+
                    "&grant_type=authorization_code\n";

            return userService.login(name, apiStr);
            /*if(userService.g)
            userService.addUser(name,openId);*/
    }




}
