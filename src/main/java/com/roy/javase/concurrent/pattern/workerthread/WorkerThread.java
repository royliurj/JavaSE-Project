package com.roy.javase.concurrent.pattern.workerthread;

import java.util.Random;

/**
 * 加工工人
 * @Author: Roy
 * @Date: 2019/4/22 9:42
 */
public class WorkerThread extends Thread{
    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            channel.take().execute();

            try {
                //模拟对半成品的加工操作
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
