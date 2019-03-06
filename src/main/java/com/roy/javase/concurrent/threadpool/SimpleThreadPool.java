package com.roy.javase.concurrent.threadpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 简单线程池
 * @Author: Roy
 * @Date: 2019/3/6 9:26
 */
public class SimpleThreadPool extends Thread{

    //线程池大小
    private int size;
    private final int queueSize;

    //默认处理的任务总数，超出则拒绝
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private static volatile int seq = 0;

    private final static String THREAD_PREFIX = "Simple_Thread_Pool-";

    //线程组
    final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    //线程队列
    final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    //任务队列
    private final static LinkedList<Runnable> TASK_QUENE = new LinkedList<>();

    //线程池是否销毁
    private volatile boolean destroy = false;

    //初始线程池开启min个
    private int min;
    //不够用时提交到active个
    private int active;
    //在不够用时提到max个
    private int max;

    //打断策略，超出指定可执行任务数，进行打断
    private final DiscardPolicy discardPolicy ;

    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This task");
    };

    public SimpleThreadPool(){
        this(4,8,12,DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    /**
     * 初始化线程队列，线程池中的线程总数
     */
    private void init(){
        for (int i = 0; i < this.min ; i++){
            createWorkTask();
        }
        this.size = this.min;
        this.start();
   }

    //创建具体工作任务
    private void createWorkTask(){
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    @Override
    public void run() {
        while (!destroy){
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    this.min,this.active,this.max,this.size,TASK_QUENE.size());
            try {
                Thread.sleep(5_000L);
                //自动扩充线程数
                if(TASK_QUENE.size() > active && size < active){
                    for (int i = size ; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to active.");
                    this.size = active;
                } else if(TASK_QUENE.size() > max && size < max){
                    for (int i = size ; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to max.");
                    this.size = max;
                }

                synchronized (TASK_QUENE) {
                    //减容
                    if (TASK_QUENE.isEmpty() && size > active) {
                        System.out.println("======Reduce=======");
                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkerTask workerTask = it.next();
                            workerTask.close();
                            workerTask.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        this.size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //停止线程池，里面的线程队列没有任何在进行
    public void shutdown() throws InterruptedException {
        while (!TASK_QUENE.isEmpty()){
            Thread.sleep(50);
        }

        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkerTask task : THREAD_QUEUE) {
                    if (task.getTaskState() == TaskState.BLOCKED) {
                        task.interrupt();
                        task.close();
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }

        System.out.println(GROUP.activeCount());

        this.destroy = true;
        System.out.println("The thread pool has disposed");
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestroy(){
        return this.destroy;
    }

    public int getMin() {
        return min;
    }

    public int getActive() {
        return active;
    }

    public int getMax() {
        return max;
    }

    /**
     * 提交任务
     * @param runnable
     */
    public void submit(Runnable runnable){
        if(this.destroy){
            throw new IllegalStateException("The thread pool already destroy and not allow submit task");
        }
        //将用户的任务添加到任务队列
        synchronized (TASK_QUENE){
            //如果进程队列大于定义的队列大小，则拒绝
            if(TASK_QUENE.size() >= queueSize){
                discardPolicy.discard();
            }
            TASK_QUENE.addLast(runnable);
            //通知正在等待的人，有任务来了
            TASK_QUENE.notifyAll();
        }
    }

    public static class DiscardException extends RuntimeException{
        public DiscardException(String message){
            super(message);
        }
    }

    public interface DiscardPolicy{

        void discard() throws DiscardException;
    }

    /**
     * 任务状态
     */
    private enum TaskState{
        FREE,RUNNING, BLOCKED,DEAD
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
                            taskState = TaskState.BLOCKED;
                            TASK_QUENE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Closed.");
                            //打断后直接跳出最外层循环，到达Label处
                            break OUTER;
                        }
                    }

                    //取出最先进入的任务
                    runnable = TASK_QUENE.removeFirst();
                }

                //执行Runable
                if(runnable != null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

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

        Thread.sleep(10_000L);

        threadPool.shutdown();
    }
}
