package com.roy.javase.concurrent.pattern.future;

/**
 * 会一直等待get执行完
 * @Author: Roy
 * @Date: 2019/3/25 9:19
 */
public class SyncInvoker {

    public static void main(String[] args) {

        System.out.println("=====Begin=====");
        System.out.println(get());
        System.out.println("=====End=====");
    }

    public static String get(){
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Finish!!!";
    }
}
