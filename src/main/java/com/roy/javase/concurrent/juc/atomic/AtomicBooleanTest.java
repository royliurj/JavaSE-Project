package com.roy.javase.concurrent.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: Roy
 * @Date: 2019/4/29 10:05
 */
public class AtomicBooleanTest {
    private static final AtomicBoolean flag = new AtomicBoolean(true);
    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                while (flag.get()){
                    try {
                        Thread.sleep(1000);
                        System.out.println("I am working");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("I am finish");
            }
        }.start();

        Thread.sleep(5000L);

        new Thread(){
            @Override
            public void run() {
                flag.compareAndSet(true, false);
                System.out.println("Set flag to false");
            }
        }.start();
    }
}
