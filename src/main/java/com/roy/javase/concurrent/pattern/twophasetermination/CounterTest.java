package com.roy.javase.concurrent.pattern.twophasetermination;

/**
 * @Author: Roy
 * @Date: 2019/4/19 13:37
 */
public class CounterTest {
    public static void main(String[] args) {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        try {
            Thread.sleep(10_000L);
            counterIncrement.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
