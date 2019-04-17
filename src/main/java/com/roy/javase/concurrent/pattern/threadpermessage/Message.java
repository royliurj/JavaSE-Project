package com.roy.javase.concurrent.pattern.threadpermessage;

/**
 * @Author: Roy
 * @Date: 2019/4/17 9:20
 */
public class Message {
    private final String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
