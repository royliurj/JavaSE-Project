package com.roy.javase.concurrent.pattern.pc;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Roy
 * @Date: 2019/4/17 9:25
 */
public class ProducerThread extends Thread {

    private final MessageQueue queue;

    private final static Random random = new Random(System.currentTimeMillis());

    private final static AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue queue, int seq) {
        super("Producer - " + seq);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){

            Message message = new Message("Message - " + counter.getAndIncrement());
            queue.put(message);
            System.out.println(Thread.currentThread().getName() + " put message " + message.getData());
        }
    }
}
