package com.roy.javase.proxy.dynamicproxy.cglib.hasinterfaces.impl;

import com.roy.javase.proxy.dynamicproxy.cglib.hasinterfaces.ISomeService;

/**
 * @Author: Roy
 * @Date: 2019/2/28 17:15
 */
public class SomeServiceImpl implements ISomeService {
    @Override
    public void doFirst() {
        System.out.println("Exec do first");
    }

    @Override
    public void doSecond() {
        System.out.println("Exec do second");

    }
}
