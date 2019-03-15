package com.roy.javase.concurrent.pattern.single;

/**
 * 共享资源
 * 大门只允许一次通过一个人
 * @Author: Roy
 * @Date: 2019/3/15 9:42
 */
public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /**
     * 临界值
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address){
        this.counter ++;
        //竞争
        this.name = name;
        this.address = address;

        verify();
    }

    private void verify() {
        if(this.name.charAt(0) != this.address.charAt(0)){
            System.out.println("**********BROKEN**********" + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "No." + counter + ":" + name + "," + address;
    }
}
