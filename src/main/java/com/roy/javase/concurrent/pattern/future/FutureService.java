package com.roy.javase.concurrent.pattern.future;

import java.util.function.Consumer;

/**
 * @Author: Roy
 * @Date: 2019/3/25 9:22
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task){
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            asyncFuture.done(result);
        }).start();
        return asyncFuture;
    }

    /**
     * 带回调
     * @param task
     * @param consumer
     * @param <T>
     * @return
     */
    public <T> Future<T> submit(final FutureTask<T> task, final Consumer consumer){
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(() -> {
            T result = task.call();
            consumer.accept(result);
        }).start();
        return asyncFuture;
    }
}
