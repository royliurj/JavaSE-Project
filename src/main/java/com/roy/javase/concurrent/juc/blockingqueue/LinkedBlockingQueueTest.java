package com.roy.javase.concurrent.juc.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: Roy
 * @Date: 2019/7/1 14:28
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueueTest test = new LinkedBlockingQueueTest();

        Basket basket = test.new Basket();
        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = test.new Producer("生产者001",basket);
        Producer producer2 = test.new Producer("生产者002",basket);

        Consumer consumer = test.new Consumer("消费者001",basket);

        service.execute(producer);
        service.execute(producer2);
        service.execute(consumer);

        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }

    public class Basket{
        /**
         * 篮子，能够容纳3个苹果
         */
        BlockingQueue<String> basket = new LinkedBlockingQueue<>(3);

        /**
         * 生产苹果，放入篮子
         */
        public void produce() throws InterruptedException {
            //put方法放入一个苹果，若满了，则等待
            basket.put("An apple");
        }

        public String consume() throws InterruptedException {
            //take 取走一个苹果，若为空，则等待
            return basket.take();
        }
    }

    //苹果生产者
    class Producer implements Runnable{

        private final String instance;

        private final Basket basket;

        Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        @Override
        public void run() {
            try {
                while (true){
                    System.out.println(instance + "生产苹果");
                    basket.produce();

                    Thread.sleep(300);
                }
            } catch (Exception e){
                System.out.println("Producer Interrupted");
            }
        }
    }

    class Consumer implements Runnable{
        private final String instance;
        private final Basket basket;

        Consumer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        @Override
        public void run() {
            try{
                while (true){
                    System.out.println(instance + "消费苹果" + basket.consume());

                    Thread.sleep(200);
                }
            }catch (Exception e){
                System.out.println("Consumer Interrupted");
            }

        }
    }
}
