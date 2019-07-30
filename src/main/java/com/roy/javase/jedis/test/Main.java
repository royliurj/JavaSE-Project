package com.roy.javase.jedis.test;

import com.roy.javase.jedis.service.RedisService;
import com.roy.javase.jedis.service.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: Roy
 * @Date: 2019/7/30 10:30
 */
public class Main {

    public static void main(String[] args) {
        test1();
    }

    private static void test1(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        poolConfig.setMaxWaitMillis(3);

        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.50.232", 6798);
        Jedis jedis = jedisPool.getResource();
        jedis.set("bbb", "bbb");
        String bbb = jedis.get("bbb");
        System.out.println(bbb);

//        Jedis jedis = new Jedis("192.168.50.232", 6798);  //指定Redis服务Host和port
////        jedis.auth("xxxx"); //如果Redis服务连接需要密码，制定密码
//        jedis.set("abc", "Hello World");
//        String value = jedis.get("abc"); //访问Redis服务
//        System.out.println("Key: abc, value: " + value);
//        jedis.close(); //使用完关闭连接
    }
}
