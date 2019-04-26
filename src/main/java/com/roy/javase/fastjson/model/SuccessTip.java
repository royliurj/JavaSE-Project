package com.roy.javase.fastjson.model;

/**
 * @Author: Roy
 * @Date: 2019/4/26 11:06
 */
public class SuccessTip extends Tip {

    public SuccessTip(){
        this(200,"操作成功");
    }

    public SuccessTip(int code, String message){
        super.code = code;
        super.message = message;
    }
}
