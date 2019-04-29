package com.roy.javase.concurrent.juc.atomic;

/**
 * @Author: Roy
 * @Date: 2019/4/29 9:47
 */
public class GetLockException extends Exception {
    public GetLockException(){
        super();
    }
    public GetLockException(String message){
        super(message);
    }
}
