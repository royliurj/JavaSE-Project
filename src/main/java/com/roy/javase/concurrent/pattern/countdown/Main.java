package com.roy.javase.concurrent.pattern.countdown;

import java.util.stream.IntStream;

/**
 * @Author: Roy
 * @Date: 2019/4/17 9:50
 */
public class Main {
    public static void main(String[] args) {
        CountDown countDown = new CountDown(5);

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    new Thread(() -> {
                        System.out.println(Thread.currentThread().getName() + " begin working");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " end working");
                        countDown.down();
                    }).start();
                });

        countDown.await();
        System.out.println("Finish");
    }
}
