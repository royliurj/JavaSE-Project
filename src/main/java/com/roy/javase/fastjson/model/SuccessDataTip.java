package com.roy.javase.fastjson.model;

/**
 * @Author: Roy
 * @Date: 2019/4/26 11:17
 */
public class SuccessDataTip<T> extends SuccessTip {
    private T data;

    public SuccessDataTip(T data){
        this(200,"操作成功",data);
    }

    public SuccessDataTip(int code, String message, T data){
        super.code = code;
        super.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SuccessDataTip{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
