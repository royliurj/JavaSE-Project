package com.roy.javase.concurrent.pattern.twophasetermination;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Random;

/**
 * @Author: Roy
 * @Date: 2019/4/19 13:34
 */
public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;
    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //执行二阶段终止
            this.clean();
        }
    }

    /**
     * 二阶段终止方法
     */
    private void clean() {
        System.out.println("do some clean work for the second phase.");
    }

    public void close(){
        this.terminated = true;
        this.interrupt();
    }
}
