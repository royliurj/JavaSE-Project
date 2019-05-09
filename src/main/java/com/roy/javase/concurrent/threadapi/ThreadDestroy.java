package com.roy.javase.concurrent.threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Roy
 * @Date: 2019/2/18 10:47
 */
public class ThreadDestroy {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("i will start work");
//            while (!Thread.interrupted()){
////                System.out.println("i am working");
//            }


            for (;;){
//                System.out.println("do something");
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("i will be exiting");
        });

        thread.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("System will be shutdown");
        thread.interrupt();
    }
}
