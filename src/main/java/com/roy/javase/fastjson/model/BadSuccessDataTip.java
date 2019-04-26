package com.roy.javase.fastjson.model;

/**
 * Json反序列化code 和message不会被赋值，是SuccessTip默认构造函数的值
 * @Author: Roy
 * @Date: 2019/4/26 11:44
 */
public class BadSuccessDataTip<T> extends SuccessTip {
    private T data;

    public BadSuccessDataTip(T data){
        super();
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
