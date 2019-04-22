package com.roy.javase.concurrent.pattern.workerthread;

/**
 * @Author: Roy
 * @Date: 2019/4/22 9:58
 */
public class Main {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Roy",channel).start();
        new TransportThread("Jazz",channel).start();
        new TransportThread("Jack",channel).start();
    }
}
