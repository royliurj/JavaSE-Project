package com.roy.javase.jedis.service;

/**
 * @Author: Roy
 * @Date: 2019/7/30 11:06
 */
public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getByID = new UserKey("id");
    public static UserKey getByIName= new UserKey("name");
}

