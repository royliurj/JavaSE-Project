package com.roy.javase.concurrent.pattern.activeobjects;

/**
 * @Author: Roy
 * @Date: 2019/4/23 10:28
 */
public class ActiveObjectFactory {

    private ActiveObjectFactory(){

    }

    public static ActiveObject createActiveObject(){
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(queue);
        ActiveObjectProxy proxy = new ActiveObjectProxy(schedulerThread,servant);
        schedulerThread.start();
        return proxy;
    }
}
