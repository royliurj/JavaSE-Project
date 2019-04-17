package com.roy.javase.concurrent.pattern.threadpermessage;

import java.util.stream.IntStream;

/**
 * @Author: Roy
 * @Date: 2019/4/17 13:50
 */
public class Main {
    public static void main(String[] args) {
        final MessageHelper helper = new MessageHelper();
        IntStream.rangeClosed(0,10 )
                .forEach(i -> {
                    helper.request(new Message(String.valueOf(i)));
                });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        helper.shutdown();
    }
}
