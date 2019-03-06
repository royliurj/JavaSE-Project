package com.roy.javase.concurrent.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 简单线程池
 * @Author: Roy
 * @Date: 2019/3/6 9:26
 */
public class SimpleThreadPool {

    //线程池大小
    private final int size;

    //默认大小
    private final static int DEFAULT_SIZE = 10;

    private static volatile int seq = 0;

    private final static String THREAD_PREFIX = "Simple_Thread_Pool-";

    //线程组
    final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    //线程队列
    final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    //任务队列
    private final static LinkedList<Runnable> TASK_QUENE = new LinkedList<>();

    public SimpleThreadPool(){
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    /**
     * 初始化线程队列，线程池中的线程总数
     */
    private void init(){
        for (int i = 0; i < size; i++){
            createWorkTask();
        }
    }

    //创建具体工作任务
    private void createWorkTask(){
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    /**
     * 提交任务
     * @param runnable
     */
    public void submit(Runnable runnable){
        //将用户的任务添加到任务队列
        synchronized (TASK_QUENE){
            TASK_QUENE.addLast(runnable);
            //通知正在等待的人，有任务来了
            TASK_QUENE.notifyAll();
        }
    }

    /**
     * 任务状态
     */
    private enum TaskState{
        FREE,RUNNING,BLOCKING,DEAD
    }

    private static class WorkerTask extends Thread{

        public WorkerTask(ThreadGroup group, String name){
            super(group,name);
        }

        //volatile保证原子性，按顺序执行
        private volatile TaskState taskState = TaskState.FREE;

        public TaskState getTaskState() {
            return taskState;
        }

        /**
         * 关闭线程
         */
        public void close(){
           this.taskState = TaskState.DEAD;
        }

        /**
         * 保证线程不能关闭
         */
        @Override
        public void run() {
            OUTER:
            //没有结束就一直循环
            while (this.taskState != TaskState.DEAD){
                Runnable runnable;
                //抢锁
                synchronized (TASK_QUENE){
                    //如果队列为空，Wait
                    while (TASK_QUENE.isEmpty()){
                        try {
                            taskState = TaskState.BLOCKING;
                            TASK_QUENE.wait();
                        } catch (InterruptedException e) {
                            //打断后直接跳出最外层循环，到达Label处
                            break OUTER;
                        }
                    }

                    //取出最先进入的任务
                    runnable = TASK_QUENE.removeFirst();
                }

                //执行
                if(runnable != null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }
    }

    public static void main(String[] args) {

        SimpleThreadPool threadPool = new SimpleThreadPool();

//        for (int i = 0; i < 40; i++) {
//            threadPool.submit(() -> {
//                System.out.println("The runnable be serviced by " + Thread.currentThread().getName() + " start.");
//                try {
//                    Thread.sleep(5_000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("The runnable be serviced by " + Thread.currentThread().getName() + " finished.");
//            });
//        }


        IntStream.rangeClosed(0, 40)
                .forEach(i -> {
                    //提交40个任务
                    threadPool.submit(() -> {
                        System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread().getName() + " start.");
                        try {
                            Thread.sleep(5_000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread().getName() + " finished.");
                    });
                });
    }
}
