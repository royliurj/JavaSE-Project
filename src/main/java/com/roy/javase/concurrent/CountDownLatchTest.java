package com.roy.javase.concurrent;

import java.util.concurrent.*;

/**
 * @Author: Roy
 * @Date: 2019/5/29 14:38
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        MyTask myTask1 = new MyTask(latch);
        MyTask myTask2 = new MyTask(latch);
        MyTask myTask3 = new MyTask(latch);

        Future<String> taskFuture1 = executor.submit(myTask1);
        Future<String> taskFuture2 = executor.submit(myTask2);
        Future<String> taskFuture3 = executor.submit(myTask3);

        executor.shutdown();
        try {
            latch.await();

            System.out.println("Result1:" + taskFuture1.get());
            System.out.println("Result2:" + taskFuture2.get());
            System.out.println("Result3:" + taskFuture3.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyTask implements Callable<String> {
        private final CountDownLatch latch;

        MyTask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public String call() throws Exception {
            System.out.println("当前线程:" + Thread.currentThread().getName());
            Thread.sleep(5000);
            latch.countDown();
            return Thread.currentThread().getName();
        }
    }
}
