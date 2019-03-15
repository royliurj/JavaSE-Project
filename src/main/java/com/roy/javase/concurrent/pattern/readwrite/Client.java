package com.roy.javase.concurrent.pattern.readwrite;

/**
 * @Author: Roy
 * @Date: 2019/3/15 10:28
 */
public class Client {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData(10);

        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();

        new WriteWorker(sharedData,"qwertyuiop").start();
        new WriteWorker(sharedData,"asdfghjkl").start();
    }
}
