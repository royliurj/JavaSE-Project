package com.roy.javase.concurrent.pattern.activeobjects;

import java.util.LinkedList;

/**
 * @Author: Roy
 * @Date: 2019/4/23 9:53
 */
public class ActivationQueue {
    private final static int MAX_REQUEST_QUEUE_SIZE = 100;
    private final LinkedList<MethodRequest> queue;

    public ActivationQueue(){
        this.queue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request){
        while (queue.size() >= MAX_REQUEST_QUEUE_SIZE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.queue.addLast(request);
        this.notifyAll();
    }

    public synchronized MethodRequest take(){
        while (queue.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MethodRequest methodRequest = queue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
}
