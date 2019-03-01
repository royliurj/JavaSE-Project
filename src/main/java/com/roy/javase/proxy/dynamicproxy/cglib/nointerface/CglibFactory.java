package com.roy.javase.proxy.dynamicproxy.cglib.nointerface;

import com.roy.javase.proxy.dynamicproxy.cglib.hasinterfaces.ISomeService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib工厂
 * 要求目标类不能是final的，因为final类不能有子类
 * @Author: Roy
 * @Date: 2019/3/1 17:04
 */
public class CglibFactory implements MethodInterceptor {

    private SomeService target;

    public CglibFactory() {
        super();
    }

    public CglibFactory(SomeService target) {
        super();
        this.target = target;
    }

    public SomeService getCglibCreator(){
        //实例增强器
        Enhancer enhancer = new Enhancer();
        //指定父类，即目标类，因为Cglib动态代理增强的原理是子类增强父类。
        enhancer.setSuperclass(SomeService.class);
        //设置回调接口对象
        enhancer.setCallback(this);

        //创建Cglib动态代理对象，即目标类的子类对象
        SomeService proxy = (SomeService) enhancer.create();
        return proxy;
    }

    /**
     * Callback接口需要实现的方法
     * @param proxy
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("CGLIB无接口增强方法：" + method.getName());

        Object result = method.invoke(target, objects);
        return result;
    }
}
