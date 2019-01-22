package com.roy.javase.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Roy
 * @Date: 2019/1/22 13:40
 */
public class Main {

    public static void main(String[] args) {
        testCountDownLatch();
    }

    /**
     * 跑步比赛示例
     */
    public static void testCountDownLatch(){

        int threadCount = 10;
        //10个人参与比赛
        final CountDownLatch latch = new CountDownLatch(threadCount);
        for(int i=0; i< threadCount; i++){
            new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getId() + "开始出发");
                try {
                    //跑步过程中
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //跑到终点
                System.out.println("线程" + Thread.currentThread().getId() + "已到达终点");
                //countDown计数减1
                latch.countDown();
            }).start();
        }

        try {
            //裁判等待所有选手跑完比赛
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("10个线程已经执行完毕！开始计算排名");
    }
}
