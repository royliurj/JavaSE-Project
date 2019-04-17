package com.roy.javase.concurrent.pattern.pc;

import java.util.Random;

/**
 * @Author: Roy
 * @Date: 2019/4/17 9:29
 */
public class ConsumerThread extends  Thread {

    private final MessageQueue queue;

    private final static Random random = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue queue, int seq) {
        super("Consumer - " + seq);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){

            Message message = queue.take();
            System.out.println(Thread.currentThread().getName() + " take a message");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
