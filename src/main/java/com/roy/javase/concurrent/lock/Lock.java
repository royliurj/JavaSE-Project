package com.roy.javase.concurrent.lock;

import java.util.Collection;

/**
 * @Author: Roy
 * @Date: 2019/3/5 10:42
 */
public interface Lock {

    class TimeOutException extends Exception{
        public TimeOutException(String message){
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
