package com.roy.javase.concurrent.threadapi;

/**
 * @Author: Roy
 * @Date: 2019/2/18 11:02
 */
public class ThreadService{

    //执行线程
    private Thread executeThread = null;
    private boolean finished = false;

    public void execute(Runnable task){
        executeThread = new Thread(() -> {
            Thread runner = new Thread(task);
            //设置为守护线程
            runner.setDaemon(true);
            runner.start();

            try {
                runner.join();
                finished = true;
            } catch (InterruptedException e) {

            }
        });

        executeThread.start();
    }

    public void shutdown(long mills){
        long currentTime = System.currentTimeMillis();
        while (!finished){
            if((System.currentTimeMillis() - currentTime) >= mills){
                System.out.println("任务超时，需要结束");
                executeThread.interrupt();
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finished = false;
    }

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();

        service.execute(() -> {
            //无限执行
//            while (true){
//                //do something
//            }
            //执行5秒结束
            try {
                Thread.sleep(5_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
