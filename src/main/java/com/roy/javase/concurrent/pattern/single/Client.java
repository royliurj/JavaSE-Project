package com.roy.javase.concurrent.pattern.single;

/**
 * @Author: Roy
 * @Date: 2019/3/15 9:48
 */
public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();

        User bj = new User("Baa","Beijing",gate);
        User sh = new User("Sbb","Shanghai",gate);
        User gj = new User("Gcc","Guangzhou",gate);

        bj.start();
        sh.start();
        gj.start();
    }
}
