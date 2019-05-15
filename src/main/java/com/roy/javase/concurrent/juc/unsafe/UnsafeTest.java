package com.roy.javase.concurrent.juc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Roy
 * @Date: 2019/5/15 9:46
 */
public class UnsafeTest {
    private static Unsafe getUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        /*
        获取Unsafe对象
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
        System.out.println(unsafe.getClass());
        System.out.println(unsafe.getClass().getClassLoader());
        */

        /**
         * StupidCounter : 没有设置锁，线程共享资源
         *      Counter Result: 9663384
         *      Time ms: 356
         *
         * SyncCounter ： 使用synchronized关键字
         *      Counter Result: 10000000
         *      Time ms: 579
         *
         * LockCounter : 使用Lock
         *      Counter Result: 10000000
         *      Time ms: 295
         *
         *  AtomicCounter：使用原子类型
         *      Counter Result: 10000000
         *      Time ms: 348
         *
         *  CASCounter：使用CAS unsafe
         *      Counter Result: 10000000
         *      Time ms: 1084
         */
        ExecutorService service = Executors.newFixedThreadPool(1000);
        Counter counter = new CASCounter();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            service.execute(new CountreRunnable(counter,10000));
        }
        //打断线程池内，执行的线程
        service.shutdown();
        //等待一个小时后结束，如果不需要等待那么长时间，就直接结束
        service.awaitTermination(1, TimeUnit.HOURS);

        long end = System.currentTimeMillis();
        System.out.println("Counter Result: " + counter.getCounter());
        System.out.println("Time ms: " + (end - start));
    }

    interface Counter{
        void increment();
        long getCounter();
    }

    static class StupidCounter implements Counter{
        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter{
        private AtomicLong counter = new AtomicLong();

        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }

    static class SyncCounter implements Counter{
        private long counter = 0;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class LockCounter implements Counter{
        private long counter = 0;

        private final Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class CASCounter implements Counter{
        private volatile long counter = 0;
        private Unsafe unsafe;
        private long offset;

        CASCounter() throws NoSuchFieldException {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = counter;
            while (!unsafe.compareAndSwapLong(this, offset, current, current+1)){
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class CountreRunnable implements Runnable{

        private final Counter counter;
        private final int num;

        CountreRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }
}
