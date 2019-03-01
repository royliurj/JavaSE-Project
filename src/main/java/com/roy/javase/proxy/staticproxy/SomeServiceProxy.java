package com.roy.javase.proxy.staticproxy;

/**
 * 静态代理类
 * @Author: Roy
 * @Date: 2019/3/1 15:25
 */
public class SomeServiceProxy implements ISomeService {

    ISomeService target;

    public SomeServiceProxy(ISomeService target){
        this.target = target;
    }

    @Override
    public void doFirst() {
        //增强代码
        System.out.println("我增强了doFirst方法");
        target.doFirst();
    }

    @Override
    public void doSecond() {
        //增强代码
        System.out.println("我增强了doSecond方法");
        target.doSecond();
    }
}
