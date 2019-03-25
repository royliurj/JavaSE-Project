package com.roy.javase.concurrent.pattern.future;

/**
 * @Author: Roy
 * @Date: 2019/3/25 9:22
 */
public interface Future<T> {
    T get() throws InterruptedException;
}
