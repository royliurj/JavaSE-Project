package com.roy.javase.concurrent.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Roy
 * @Date: 2019/6/20 10:18
 */
public class Main {

    private int i = 0;
    private boolean isProduced = false;
    final Lock lock = new ReentrantLock();
    final Condition proCondition = lock.newCondition();
    final Condition conCondition = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            //已经生产了，等待
            if (isProduced) {
                proCondition.await();
            } else {
                //生产了以后，通知消费者
                i++;
                System.out.println("Product -> " + i);
                isProduced = true;
                conCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            //如果生成了，则消费
            if(isProduced){
                System.out.println("Consume -> " + i);
                proCondition.signal();
                isProduced = false;
            } else {
                //没有生成，等待
                conCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        ExecutorService service = Executors.newFixedThreadPool(10);

        //生产线程
        Thread produceThread = new Thread(){
            @Override
            public void run() {
                while (true){
                    main.produce();
                }
            }
        };
        //消费线程
        Thread consumeThread = new Thread(){
            @Override
            public void run() {
                while (true){
                    main.consume();
                }
            }
        };

        service.execute(produceThread);
        service.execute(consumeThread);



        service.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Other");
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        service.shutdown();
    }
}
