package com.roy.javase.concurrent.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Roy
 * @Date: 2019/4/29 9:46
 */
public class CompareAndSetLock {
    private final AtomicInteger value = new AtomicInteger(0);
    private Thread currentThread;

    public void tryLock() throws GetLockException{
        boolean success = value.compareAndSet(0, 1);
        if(!success){
            throw new GetLockException("Thread " + Thread.currentThread().getName() + " : Get the lock failed");
        } else{
            currentThread = Thread.currentThread();
        }
    }

    public void unlock(){
        if(0 == value.get()){
            return;
        }
        if(currentThread == Thread.currentThread()){
            value.compareAndSet(1, 0);
        }
    }
}
