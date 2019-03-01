package com.roy.javase.proxy.staticproxy;

import com.roy.javase.proxy.staticproxy.impl.SomeServiceImpl;

/**
 * @Author: Roy
 * @Date: 2019/2/28 17:14
 */
public class StaticProxy {
    public static void main(String[] args) {
        ISomeService target = new SomeServiceImpl();
        SomeServiceProxy proxy = new SomeServiceProxy(target);
        proxy.doFirst();
        proxy.doSecond();
    }
}
