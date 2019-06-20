package com.roy.javase.test;

/**
 * @Author: Roy
 * @Date: 2019/6/10 11:40
 */
public class Test2 {
    public static void main(String[] args) {

        System.out.println(1 << 10);

//        swap();
    }

    private static void swap() {
        int a = 5;
        int b = 10;

        System.out.println("a = " + a + "; b = " + b);

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("a = " + a + "; b = " + b);

        a = 5;
        b = 10;

        System.out.println("a = " + a + "; b = " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a = " + a + "; b = " + b);
    }
}
