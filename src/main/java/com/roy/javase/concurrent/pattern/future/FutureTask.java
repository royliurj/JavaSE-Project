package com.roy.javase.concurrent.pattern.future;

/**
 * @Author: Roy
 * @Date: 2019/3/25 9:23
 */
public interface FutureTask<T> {
    T call();
}
