package com.roy.javase.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Roy
 * @Date: 2019/1/22 15:58
 */
public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        new Thread(){
            @Override
            public void run() {
                test.get2(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.get2(Thread.currentThread());
            };
        }.start();

    }

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }

    public void get2(Thread thread) {
        rwl.readLock().lock();
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }
}
