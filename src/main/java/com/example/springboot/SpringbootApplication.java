package com.example.springboot;

import com.example.springboot.service.StationService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.springboot.mapper")
/*@ComponentScan(basePackages = {
        //"com.example.springboot.config",
        "com.example.springboot.controller",
        "com.example.springboot.service"})*/
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
