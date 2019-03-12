package com.roy.javase.concurrent.volatiletest;

/**
 * @Author: Roy
 * @Date: 2019/3/12 14:50
 */
public class VolatileTest {
    //volatile使程序正常运行，不添加，则只有Updater运行，Reader不会正常运行
    public volatile static int INIT_VALUE = 0;
    public static int MAX_LIMIT = 50;
    public static void main(String[] args) {

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue <= MAX_LIMIT){
                if(localValue != INIT_VALUE){
                    System.out.println("Reader: INIT_VALUE updated to " + INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"Reader").start();

        new Thread(() -> {
            while (INIT_VALUE <= MAX_LIMIT){
                System.out.println("Updater: update INIT_VALUE to " + (++INIT_VALUE));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();
    }
}
