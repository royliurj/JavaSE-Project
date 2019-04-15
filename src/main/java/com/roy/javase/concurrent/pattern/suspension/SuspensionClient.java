package com.roy.javase.concurrent.pattern.suspension;

/**
 * @Author: Roy
 * @Date: 2019/4/15 10:39
 */
public class SuspensionClient {
    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue,"Roy").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        Thread.sleep(15000);
        serverThread.close();
    }
}
