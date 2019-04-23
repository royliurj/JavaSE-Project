package com.roy.javase.concurrent.pattern.activeobjects;

/**
 * @Author: Roy
 * @Date: 2019/4/23 10:34
 */
public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakeClientThread("Roy",activeObject).start();
        new MakeClientThread("Pac",activeObject).start();

        new DisplayClientThread("Jazz",activeObject).start();
    }
}
