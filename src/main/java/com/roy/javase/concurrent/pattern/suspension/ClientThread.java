package com.roy.javase.concurrent.pattern.suspension;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Random;

/**
 * @Author: Roy
 * @Date: 2019/4/15 10:34
 */
public class ClientThread extends Thread {

    private final RequestQueue queue;
    private final Random random;
    private final String sendValue;

    public ClientThread(RequestQueue queue, String sendValue) {
        this.queue = queue;
        this.sendValue = sendValue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Client -> request " + sendValue);
            queue.putRequest(new Request(sendValue));

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
