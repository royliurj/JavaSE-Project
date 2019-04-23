package com.roy.javase.concurrent.pattern.activeobjects;

/**
 * @Author: Roy
 * @Date: 2019/4/23 9:42
 */
public class RealResult implements Result {
    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return resultValue;
    }
}
