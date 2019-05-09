package com.roy.javase.classloader;

/**
 * @Author: Roy
 * @Date: 2019/5/9 9:31
 */
public class Singleton {
    /**
     * 放在这个位置的时候，x=0
     * 1. 链接-准备阶段，分配空间，并初始化为默认值
     *      instance = null;int x = 0;int y = 0;
     * 2. 初始化阶段，赋值初始值
     *      instance = new Singleton(); x=1;y=1
     *      x = 0;
     *      y没有初始值，不赋值。所以y = 1
     */
    //private static Singleton instance = new Singleton();
    public static int x = 0;
    public static int y;

    /**
     * 1. 链接-准备阶段，分配空间，并初始化为默认值
     *      int x = 0;int y = 0;instance = null;
     * 2. 初始化阶段，赋值初始值
     *      x = 0;
     *      y没有初始值，不赋值。所以y = 0;
     *      instance = new Singleton()；x=1;y=1;
     */
    private static Singleton instance = new Singleton();

    private Singleton(){
        x++;
        y++;
    }

    public static Singleton getInstannce(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = getInstannce();
        System.out.println(Singleton.x);
        System.out.println(Singleton.y);
    }
}
