package com.roy.javase.concurrent.pattern.observer;

import java.util.List;

/**
 * 具体监听器，具体Observer
 * @Author: Roy
 * @Date: 2019/3/13 11:16
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {
    private final Object LOCK = new Object();

    /**
     * 业务实现，并发查询，根据传入参数，并发进行查询业务
     * @param ids
     */
    public void concurrentQuery(List<String> ids){
        if(null == ids || ids.isEmpty()){
            return;
        }

        ids.stream().forEach(id -> {
            new Thread(new ObserverableRunnable(this) {
                @Override
                public void run() {
                    try {
                        notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                        System.out.println("query for the id " + id);
                        Thread.sleep(1000L);
                        int a = 10 /0;
                        notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
                    } catch (Exception e) {
                        notifyChange(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),e));
                    }
                }
            },id).start();
        });
    }

    /**
     * 监听器接口
     * @param event
     */
    @Override
    public void onEvent(ObserverableRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println("The runnable [" + event.getThread().getName() + "] data changed and state is [" + event.getState() + "]");

            if(null != event.getCause()){
                System.out.println("The runnable [" + event.getThread().getName() + "] process failed.");
                event.getCause().printStackTrace();
            }
        }
    }
}
