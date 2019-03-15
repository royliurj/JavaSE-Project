package com.roy.javase.concurrent.pattern.readwrite;

/**
 * 读线程
 * @Author: Roy
 * @Date: 2019/3/15 10:26
 */
public class ReadWorker extends Thread {

    private final SharedData data;

    public ReadWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true){
                char[] readBuf = data.read();
                System.out.println(Thread.currentThread().getName() + " read " + String.valueOf(readBuf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
