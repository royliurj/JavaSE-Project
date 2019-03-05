package com.roy.javase.concurrent.lock;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: Roy
 * @Date: 2019/3/5 10:58
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        //实例化锁
        final BooleanLock booleanLock = new BooleanLock();


        Stream.of("T1","T2","T3","T4")
                .forEach(name -> {
                    new Thread(() -> {
                        try {
                            //先获取锁
                            booleanLock.lock(10L);
                            System.out.println(Thread.currentThread().getName() + " get the lock");
                            //工作
                            work();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Lock.TimeOutException e) {
                            System.out.println(Thread.currentThread().getName() + " time out");
//                            e.printStackTrace();
                        } finally {
                            booleanLock.unlock();
                        }
                    },name).start();
                });

//        //这里主线程有机会释放锁。是个bug
//        Thread.sleep(1000);
//        booleanLock.unlock();
    }

    /**
     * 模拟工作
     * @throws InterruptedException
     */
    private static void work() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is working...");
        Thread.sleep(10_000L);
    }
}
