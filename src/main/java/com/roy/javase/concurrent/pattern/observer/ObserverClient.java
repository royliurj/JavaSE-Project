package com.roy.javase.concurrent.pattern.observer;

/**
 * @Author: Roy
 * @Date: 2019/3/13 11:42
 */
public class ObserverClient {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);

        subject.setState(10);
        subject.setState(8);
    }
}
