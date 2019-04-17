package com.roy.javase.concurrent.pattern.pc;

/**
 * @Author: Roy
 * @Date: 2019/4/17 9:31
 */
public class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();

        new ProducerThread(queue,1).start();
        new ProducerThread(queue,2).start();
        new ProducerThread(queue,3).start();
        new ConsumerThread(queue,1).start();
        new ConsumerThread(queue,2).start();
    }
}
