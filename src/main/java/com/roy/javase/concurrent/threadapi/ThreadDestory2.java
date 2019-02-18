package com.roy.javase.concurrent.threadapi;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Roy
 * @Date: 2019/2/18 10:55
 */
public class ThreadDestory2 {

    static class MyTask extends Thread{
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work");
            while (!closed && !isInterrupted()){
                //正在运行
            }
            System.out.println("I will be exiting");
        }

        public void close(){
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask t = new MyTask();
        t.start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("System will be shutdown");
        t.close();
    }
}
