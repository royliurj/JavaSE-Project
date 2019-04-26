package com.roy.javase.fastjson.model;

/**
 * @Author: Roy
 * @Date: 2019/4/26 11:06
 */
public abstract class Tip {

    protected int code;

    protected String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
