package com.roy.javase.concurrent.volatiletest;

/**
 * @Author: Roy
 * @Date: 2019/3/12 17:11
 */
public class VolatileTest2 {
    public volatile static int INIT_VALUE = 0;
    public static int MAX_LIMIT = 500;
    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE <= MAX_LIMIT){
                System.out.println("T1:" + (++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();

        new Thread(() -> {
            while (INIT_VALUE <= MAX_LIMIT){
                System.out.println("T2:" + (++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T2").start();
    }
}
