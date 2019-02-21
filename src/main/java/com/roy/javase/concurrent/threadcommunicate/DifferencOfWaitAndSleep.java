package com.roy.javase.concurrent.threadcommunicate;

import javax.sound.midi.Soundbank;
import java.util.stream.Stream;

/**
 * @Author: Roy
 * @Date: 2019/2/21 9:34
 */
public class DifferencOfWaitAndSleep {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        Stream.of("T1","T2").forEach(name -> {
            new Thread(() -> {
//                m1();
                m2();
            },name).start();
        });

    }

    /**
     * 先获得锁的线程，必须sleep5秒后，下一个进程才能进入
     */
    public static void m1(){
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName() + " enter.");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 先获得锁的进程，执行到wait时，就会释放锁，后一个进程就可以获得
     */
    public static void m2(){
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName() + " enter.");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
