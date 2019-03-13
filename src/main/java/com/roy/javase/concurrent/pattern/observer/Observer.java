package com.roy.javase.concurrent.pattern.observer;

/**
 * @Author: Roy
 * @Date: 2019/3/13 11:34
 */
public abstract class Observer {

    protected final Subject subject;

    protected Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }


    protected abstract void update();
}
