package com.roy.javase.concurrent.pattern.readwrite;

/**
 * 读写锁
 *
 * @Author: Roy
 * @Date: 2019/3/15 10:04
 */
public class ReadWriteLock {

    //正在读的个数
    private int readingReaders = 0;
    //等待读的个数
    private int waitingReaders = 0;
    //正在写的个数
    private int writingWriters = 0;
    //等待写的个数
    private int waitingWriters = 0;
    //是否更加偏爱写，优先级高点
    private boolean preferWrite = true;

    public ReadWriteLock(){
        this(true);
    }

    public ReadWriteLock(boolean preferWrite){
        this.preferWrite = preferWrite;
    }

    /**
     * 读锁
     */
    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            //当有写存在时，不能读，等待, 或者偏爱写，并且有等待写的线程
            while (this.writingWriters > 0 || (preferWrite && this.waitingWriters > 0)) {
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.waitingReaders--;
        }
    }

    /**
     * 释放读锁
     */
    public synchronized void readUnlocak() {
        this.readingReaders--;
        //通知其它正在wait的线程
        this.notifyAll();
    }

    /**
     * 写锁
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            //当存在读或者写的操作是，等待
            while (this.readingReaders > 0 || this.writingWriters > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnlock(){
        this.writingWriters--;
        this.notifyAll();
    }
}
