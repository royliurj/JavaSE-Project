package com.roy.javase.proxy.dynamicproxy.cglib.hasinterfaces;

import com.roy.javase.proxy.dynamicproxy.cglib.hasinterfaces.impl.SomeServiceImpl;

/**
 * @Author: Roy
 * @Date: 2019/3/1 17:02
 */
public class DynamicProxyCGLIBMain {

    public static void main(String[] args) {
        ISomeService target = new SomeServiceImpl();
        CglibFactory proxy = new CglibFactory(target);
        proxy.getCglibCreator().doFirst();

    }
}
