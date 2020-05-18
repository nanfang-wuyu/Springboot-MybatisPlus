package com.example.springboot;

import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.delayTest.TestAsyncService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Autowired
    TestAsyncService testAsyncService;

    @Test
    public void test() throws IOException, InterruptedException {
        System.out.println("发货通知调用开始！");

        int[] taskArrays = new int[]{2000, 5000, 10000};
        testAsyncService.testNotice(taskArrays);

        System.out.println("已经开始通知,异步执行通知");
    }

}
