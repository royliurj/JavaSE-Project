package com.roy.javase.concurrent.pattern.workerthread;

/**
 * 半成品
 * @Author: Roy
 * @Date: 2019/4/22 9:41
 */
public class Request{
    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }

    @Override
    public String toString() {
        return "Request -> No.=" + number + " Number=" + number;
    }
}
