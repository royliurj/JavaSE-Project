package com.roy.javase.jedis.config;

import com.roy.javase.jedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: Roy
 * @Date: 2019/7/30 10:28
 */
@Configuration
public class RedisPoolFactory {
//    @Autowired
//    RedisConfig redisConfig;

    @Value("${redis.host}")
    String host;
    @Value("${redis.port}")
    String port;
    @Value("${redis.poolMaxTotal}")
    String poolMaxTotal;
    @Value("${redis.poolMaxIdle}")
    String poolMaxIdle;
    @Value("${redis.poolMaxWait}")
    String poolMaxWait;

    @Bean
    public RedisConfig redisConfig(){
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setHost("192.168.50.232");
        redisConfig.setPort(6798);
        redisConfig.setPoolMaxIdle(10);
        redisConfig.setPoolMaxTotal(10);
        redisConfig.setPoolMaxWait(3);

        return redisConfig;
    }

    @Bean
    public JedisPool JeditPoolFactory(){
        //设置JedisPoolConfig
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        RedisConfig redisConfig = redisConfig();
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait());

        //实例化JedisPool
        String host = redisConfig().getHost();
        int port = redisConfig().getPort();
        JedisPool jp = new JedisPool(poolConfig, host, port);
        return jp;
    }

    @Bean
    public RedisService redisService(){
        return new RedisService();
    }
}
