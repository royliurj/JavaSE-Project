package com.roy.javase.concurrent.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author: Roy
 * @Date: 2019/4/29 9:35
 */
public class AtomicIntegerTest {
    private static boolean flag = true;
    private static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) throws InterruptedException {


//        AtomicInteger value = new AtomicInteger(10);
//        boolean b = value.compareAndSet(10, 20);
//        System.out.println(b);
//        System.out.println(value.get());



//        jitTest();

        IntStream.rangeClosed(1, 2).forEach(i -> new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                doSomething();
            } catch (GetLockException e) {
                e.printStackTrace();
            }
        },String.valueOf(i)).start());

//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    doSomething();
//                } catch (GetLockException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    doSomething();
//                } catch (GetLockException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }

    private static void doSomething() throws GetLockException {
        try {
            lock.tryLock();
            System.out.println("Thread " + Thread.currentThread().getName() + " : 获取了lock");
            Thread.sleep(100_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void jitTest() throws InterruptedException {
        new Thread(() -> {
            while (flag){
                //JIT进行了优化，如果有下面的代码，会结束掉程序
                //System.out.println(Thread.currentThread().getName());
            }
        }).start();

        Thread.sleep(2000L);

        new Thread(() -> {
            flag = false;
            System.out.println("set flag to false");
        }).start();
    }
}
