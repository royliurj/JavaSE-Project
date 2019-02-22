package com.roy.javase.concurrent.other;

import java.util.*;

/**
 * @Author: Roy
 * @Date: 2019/2/22 9:47
 */
public class CaptureService {

    final static LinkedList<Control> CONTROLS = new LinkedList<>();

    final static int MAX_WORKER = 5;

    public static void main(String[] args) {

        List<Thread> worker = new ArrayList<>();

        //有10个线程，线程数最多为5个一起运行。
        Arrays.asList("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10").stream()
                .map(CaptureService::createThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });

        //join线程，等待一起执行完毕后，在执行后面的逻辑
        worker.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //全部线程执行完毕
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    public static Thread createThread(String name){
        return new Thread(() -> {
            Optional.of("The worker " + Thread.currentThread().getName() + " begin capture data").ifPresent(System.out::println);

            //通过锁的方式，保证最多只有5个线程一起运行
            synchronized (CONTROLS){
                while (CONTROLS.size() >= 5){
                    //线程池中如果已经有5个线程了，则其它线程等待
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                CONTROLS.addLast(new Control());
            }
            //线程正在运行
            Optional.of("The worker " + Thread.currentThread().getName() + " is working").ifPresent(System.out::println);
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //线程执行完毕后，通知正在等待的其它线程
            synchronized (CONTROLS){
                Optional.of("The worker " + Thread.currentThread().getName() + " end capture data").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        },name);
    }

    private static class Control{

    }
}
