package com.roy.javase.concurrent.threadcommunicate;

/**
 * @Author: Roy
 * @Date: 2019/2/20 14:41
 */
public class ProducerConsumerVersion1 {
    private int i = 0;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                //生产了，则等待
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //没有生产，则生产
                i++;
                System.out.println("P1->" + i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduced) {
                //如果生产了，则消费
                System.out.println("C1->" + i);
                LOCK.notify();
                isProduced = false;
            } else {
                //如果没有生产，则等待
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion1 pc = new ProducerConsumerVersion1();

        new Thread(() -> {
            while (true){
                pc.produce();
            }
        }).start();

        new Thread(() -> {
            while (true){
                pc.consume();
            }
        }).start();
    }
}
