package com.roy.javase.proxy.dynamicproxy.cglib.nointerface;

import com.roy.javase.proxy.dynamicproxy.jdk.ISomeService;

/**
 * @Author: Roy
 * @Date: 2019/2/28 17:15
 */
public class SomeService {

    public void doFirst() {
        System.out.println("Exec do first");
    }

    public void doSecond() {
        System.out.println("Exec do second");
    }
}
