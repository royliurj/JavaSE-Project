package com.roy.javase.concurrent.pattern.single;

/**
 * @Author: Roy
 * @Date: 2019/3/15 9:46
 */
public class User extends Thread {

    private final String name;
    private final String address;
    private final Gate gate;

    public User(String name, String address, Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(name + " Begin");
        while (true){
            this.gate.pass(name, address);
        }
    }
}
