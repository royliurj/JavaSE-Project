package com.roy.javase.concurrent.pattern.pc;

import java.util.LinkedList;

/**
 * @Author: Roy
 * @Date: 2019/4/17 9:20
 */
public class MessageQueue {
    private final static int DEFAULT_MAX_LIMIT = 100;
    private final int limit;

    private LinkedList<Message> queue;
    public MessageQueue(){
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public void put(Message message){
        synchronized (queue){
            while (queue.size() > limit){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take(){
        synchronized (queue){
            while (queue.isEmpty()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }
}
