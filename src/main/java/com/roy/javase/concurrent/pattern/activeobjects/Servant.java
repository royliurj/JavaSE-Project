package com.roy.javase.concurrent.pattern.activeobjects;

/**
 * @Author: Roy
 * @Date: 2019/4/23 9:42
 */
class Servant implements ActiveObject {

    @Override
    public Result makeString(int count, char fillChar) {
        char[] buf = new char[count];
        try {
            for (int i = 0; i < count; i++) {
                buf[i] = fillChar;
                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RealResult(new String(buf));
    }

    @Override
    public void displayString(String text) {
        try {
            System.out.println("Display: " + text);
            Thread.sleep(10);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
