package com.roy.javase.concurrent.pattern.countdown;

/**
 * @Author: Roy
 * @Date: 2019/4/17 9:48
 */
public class CountDown {
    private final int total;

    private int counter = 0;

    public CountDown(int total) {
        this.total = total;
    }

    public void down(){
        synchronized (this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await(){
        synchronized (this){
            while (counter != total){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
