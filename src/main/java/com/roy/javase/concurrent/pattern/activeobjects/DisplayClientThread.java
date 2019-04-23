package com.roy.javase.concurrent.pattern.activeobjects;

/**
 * @Author: Roy
 * @Date: 2019/4/23 10:30
 */
public class DisplayClientThread extends Thread {

    private final  ActiveObject activeObject;

    public DisplayClientThread(String name,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            String text = Thread.currentThread().getName() + "=> " + i;
            activeObject.displayString(text);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
