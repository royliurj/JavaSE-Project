package com.roy.javase.concurrent.threadcommunicate;

import java.util.stream.Stream;

/**
 * 真正的（多线程）生产者与消费者
 * @Author: Roy
 * @Date: 2019/2/21 9:27
 */
public class RealProducerConsumer {
    private int i = 0;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            //这里如果用if，就会出现多次生成，或者消费的情况
            while (isProduced) {
                //生产了，则等待
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //没有生产，则生产
            i++;
            System.out.println(Thread.currentThread().getName() + "->->" + i);
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    public void consume() {
        synchronized (LOCK) {
            while (!isProduced) {
                //如果没有生产，则等待
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果生产了，则消费
            System.out.println(Thread.currentThread().getName() + "->->" + i);
            LOCK.notifyAll();
            isProduced = false;
        }
    }

    public static void main(String[] args) {
        RealProducerConsumer pc = new RealProducerConsumer();

        Stream.of("P1","P2").forEach(name -> {
            new Thread(() -> {
                while (true){
                    pc.produce();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },name).start();
        });

        Stream.of("C1","C2").forEach(name -> {
            new Thread(() -> {
                while (true){
                    pc.consume();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },name).start();
        });
    }
}
