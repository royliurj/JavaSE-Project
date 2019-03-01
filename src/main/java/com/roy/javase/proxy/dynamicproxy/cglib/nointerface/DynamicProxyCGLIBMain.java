package com.roy.javase.proxy.dynamicproxy.cglib.nointerface;

/**
 * @Author: Roy
 * @Date: 2019/3/1 17:02
 */
public class DynamicProxyCGLIBMain {

    public static void main(String[] args) {
        SomeService target = new SomeService();
        CglibFactory proxy = new CglibFactory(target);
        proxy.getCglibCreator().doFirst();

    }
}
