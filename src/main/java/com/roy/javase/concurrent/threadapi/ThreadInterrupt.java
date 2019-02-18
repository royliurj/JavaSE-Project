package com.roy.javase.concurrent.threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Roy
 * @Date: 2019/2/18 10:05
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                System.out.println("线程被打断");
//                e.printStackTrace();
//            }
            while (true){
                System.out.println("thread do something");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    System.out.println("线程被打断");
                    e.printStackTrace();
                }
            }
        });

        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("thread的打断状态是：" + thread.isInterrupted());
        thread.interrupt();
        System.out.println("thread的打断状态是：" + thread.isInterrupted());
    }
}
