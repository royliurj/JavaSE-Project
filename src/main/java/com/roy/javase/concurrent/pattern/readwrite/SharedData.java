package com.roy.javase.concurrent.pattern.readwrite;

/**
 * 共享资源
 * @Author: Roy
 * @Date: 2019/3/15 10:14
 */
public class SharedData {
    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size){
        buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[0] = '*';
        }
    }

    /**
     * 读操作
     * @return
     * @throws InterruptedException
     */
    public char[] read() throws InterruptedException {
        try {
            //获取读锁
            lock.readLock();
            return this.doRead();
        } finally {
            lock.readUnlocak();
        }
    }

    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuf[i] = buffer[i];
        }
        slowly(50);
        return newBuf;
    }

    /**
     * 写操作
     * @param c
     * @throws InterruptedException
     */
    public void write(char c) throws InterruptedException {
        try{
            //获取写锁
            lock.writeLock();
            this.doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly(10);
        }
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
