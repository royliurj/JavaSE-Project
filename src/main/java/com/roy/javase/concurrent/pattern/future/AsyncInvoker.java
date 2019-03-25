package com.roy.javase.concurrent.pattern.future;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Future           -> 代表的是一个未来的凭证
 * FutureTask       -> 将你的调用逻辑进行了隔离
 * FutureService    -> 桥接Future和FutureTask
 * @Author: Roy
 * @Date: 2019/3/25 9:23
 */
public class AsyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=====Begin=====");

        FutureService service = new FutureService();
        Future<String> future = service.submit(() -> {
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finish!!!";
        },System.out::println);

        System.out.println("===do other thing.====");
        try {
            Thread.sleep(1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=====End=====");
    }
}
