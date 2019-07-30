package com.roy.javase.jedis.service;

/**
 * @Author: Roy
 * @Date: 2019/7/30 11:05
 */
public interface KeyPrefix {

    /*
    获取存活时间
     */
    int expireSeconds();

    /*
    获取前缀
     */
    String getPrefix();
}

