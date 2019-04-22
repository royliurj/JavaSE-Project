package com.roy.javase.concurrent.pattern.workerthread;

import java.util.Arrays;

/**
 * 生产线
 * @Author: Roy
 * @Date: 2019/4/22 9:40
 */
public class Channel {
    //生产线上最多的半成品数
    private final static int MAX_REQUEST = 100;
    //半成品队列
    private final Request[] requestQueue;

    private int head;
    private int tail;
    //生产线半成品个数
    private int count;

    //加工半成品的工人队列
    private final WorkerThread[] workerPool;

    public Channel(int workers) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[workers];
        init();
    }

    private void init(){
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker-" + i,this);
        }
    }

    //生产线开始工作
    public void startWorker(){
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    /**
     * 把半成品放置到生产线队列中
     * @param request
     */
    public synchronized void put(Request request){
        while (count >= requestQueue.length){
            try {
                this.wait();
            } catch (Exception e){
            }
        }

        this.requestQueue[tail] = request;
        tail = (tail+1) % requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    /**
     * 从生产线中取出一个半成品
     * @return
     */
    public synchronized Request take(){
        while (count <= 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Request request = this.requestQueue[head];
        this.head = (this.head+1) % this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
