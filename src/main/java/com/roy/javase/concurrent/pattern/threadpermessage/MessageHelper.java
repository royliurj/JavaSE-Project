package com.roy.javase.concurrent.pattern.threadpermessage;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Roy
 * @Date: 2019/4/17 13:47
 */
public class MessageHelper {
    private final static Random random = new Random(System.currentTimeMillis());
    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message){
        executor.execute(() -> {
            String value = message.getData();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will be handle by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown(){
        ((ExecutorService)executor).shutdown();
    }
}
