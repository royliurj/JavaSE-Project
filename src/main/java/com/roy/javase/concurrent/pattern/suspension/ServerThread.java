package com.roy.javase.concurrent.pattern.suspension;

import java.util.Random;

/**
 * @Author: Roy
 * @Date: 2019/4/15 10:36
 */
public class ServerThread extends Thread {

    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }


    @Override
    public void run() {
        while (!flag){
            Request request = queue.getRequest();
            if(null == request){
                continue;
            }
            System.out.println("Server -> " + request.getValue());

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){
        flag = true;
        this.interrupt();
    }
}
