package com.roy.javase.jedis;

import com.roy.javase.jedis.config.RedisPoolFactory;
import com.roy.javase.jedis.service.RedisService;
import com.roy.javase.jedis.service.UserKey;
import com.roy.javase.jedis.test.Main;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

/**
 * @Author: Roy
 * @Date: 2019/7/30 11:09
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Main.class)
public class TestRedis {

//    @Autowired
//    RedisService redisService;

    @Test
    public void testSetAndGet(){

        ApplicationContext context = new AnnotationConfigApplicationContext(RedisPoolFactory.class);
        RedisService redisService = context.getBean(RedisService.class);
        redisService.set(new UserKey("User"), "TestRedis", "abc");
        String s = redisService.get(new UserKey("User"), "TestRedis", String.class);
        System.out.println(s);
    }

}
