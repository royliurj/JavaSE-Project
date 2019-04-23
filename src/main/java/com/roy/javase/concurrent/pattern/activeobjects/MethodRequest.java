package com.roy.javase.concurrent.pattern.activeobjects;

/**
 * 对应ActiveObject的每个方法
 * @Author: Roy
 * @Date: 2019/4/23 9:48
 */
public abstract class MethodRequest {
    protected final Servant servant;
    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
