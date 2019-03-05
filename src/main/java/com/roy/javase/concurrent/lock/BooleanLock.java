package com.roy.javase.concurrent.lock;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @Author: Roy
 * @Date: 2019/3/5 10:45
 */
public class BooleanLock implements Lock {

    //false：没有人获取到锁， true：有人获取到了锁
    private boolean initValue;

    private Thread currentThread;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    public BooleanLock(){
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue){
            //已经有人获取到锁，当前线程锁定排队
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        //当前线程获取到了锁，从队列里删除
        this.initValue = true;
        blockedThreadCollection.remove(Thread.currentThread());
        this.currentThread = Thread.currentThread();
    }

    /**
     * 传入时间，指定获取锁的时间，如果超时，则抛出异常，不在等待。
     * @param mills
     * @throws InterruptedException
     * @throws TimeOutException
     */
    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if(mills <= 0){
            lock();
        }

        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;

        while (initValue){
            //超时抛出异常
            if(hasRemaining <= 0){
                throw new TimeOutException("超时");
            }

            //被占用了所
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis();
//            System.out.println(hasRemaining);
        }

        this.initValue = true;
        blockedThreadCollection.remove(Thread.currentThread());
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if(Thread.currentThread().equals(currentThread)) {
            //释放锁
            initValue = false;
            System.out.println(Thread.currentThread().getName() + " release the lock monitor.");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        //直接返回线程不安全，返回的是实例，外部可以修改。
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
