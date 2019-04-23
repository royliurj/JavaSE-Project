package com.roy.javase.concurrent.pattern.activeobjects;

/**
 * @Author: Roy
 * @Date: 2019/4/23 9:52
 */
public class DisplayStringRequest extends MethodRequest {
    private final String text;

    public DisplayStringRequest(Servant servant, String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}
