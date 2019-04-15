package com.roy.javase.concurrent.pattern.suspension;

/**
 * @Author: Roy
 * @Date: 2019/4/15 10:30
 */
public class Request {

    private final String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
