package com.roy.javase.concurrent.pattern.activeobjects;

/**
 * @Author: Roy
 * @Date: 2019/4/23 9:40
 */
public interface ActiveObject {

    Result makeString(int count, char fillChar);

    void displayString(String text);
}
