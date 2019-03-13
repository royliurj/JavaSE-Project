package com.roy.javase.concurrent.pattern.observer;

import java.util.Arrays;

/**
 * @Author: Roy
 * @Date: 2019/3/13 11:26
 */
public class ThreadLifeCycleClient {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }
}
