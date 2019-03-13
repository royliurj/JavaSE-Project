package com.roy.javase.concurrent.pattern.observer;

/**
 * 生命周期监听接口定义
 * @Author: Roy
 * @Date: 2019/3/13 11:16
 */
public interface LifeCycleListener {
    void onEvent(ObserverableRunnable.RunnableEvent event);
}
