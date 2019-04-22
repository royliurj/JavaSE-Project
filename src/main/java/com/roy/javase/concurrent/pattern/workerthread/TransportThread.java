package com.roy.javase.concurrent.pattern.workerthread;

import java.util.Random;

/**
 * 搬运半成品的工人，负责将半成品放入到生产线中
 * @Author: Roy
 * @Date: 2019/4/22 9:54
 */
public class TransportThread extends Thread {
    private final Channel channel;
    
    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name,Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            Request request = new Request(getName(),i);
            this.channel.put(request);
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
