package com.roy.javase.proxy.dynamicproxy.jdk;

import com.roy.javase.proxy.dynamicproxy.jdk.impl.SomeServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Roy
 * @Date: 2019/3/1 16:30
 */
public class DynamicProxyJDKMain {

    public static void main(String[] args) {
        ISomeService target = new SomeServiceImpl();

        ISomeService service = (ISomeService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(), //目标类的类加载器
                target.getClass().getInterfaces(),  //目标类所实现的所有接口
                new InvocationHandler() {           //内部匿名类
                    /**
                     * @param proxy 代理对象
                     * @param method 目标方法
                     * @param args 参数列表
                     * @return 返回值
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理增强了方法" + method.getName());
                        Object o = method.invoke(target, args);
                        return o;
                    }
                });

        service.doFirst();
    }
}
