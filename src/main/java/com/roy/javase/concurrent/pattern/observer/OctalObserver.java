package com.roy.javase.concurrent.pattern.observer;

/**
 * @Author: Roy
 * @Date: 2019/3/13 11:40
 */
public class OctalObserver extends Observer {

    protected OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    protected void update() {
        System.out.println("Binary " + Integer.toOctalString(subject.getState()));
    }
}
