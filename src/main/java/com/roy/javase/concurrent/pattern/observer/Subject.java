package com.roy.javase.concurrent.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Roy
 * @Date: 2019/3/13 11:35
 */
public class Subject {
    private final List<Observer> list = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if(this.state == state){
            return;
        }
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        list.add(observer);
    }

    private void notifyAllObservers(){
        list.stream().forEach(observer -> {
            observer.update();
        });
    }


}
