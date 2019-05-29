package com.roy.javase.concurrent.juc.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author: Roy
 * @Date: 2019/5/15 10:06
 */
public class UnsafeFooTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

//        Simple simple = new Simple();
//        System.out.println(simple.get());

        //反射会调用类的初始化
//        Simple.class.newInstance();
        //类加载器不会调用初始化
        //Class.forName("com.roy.javase.concurrent.juc.unsafe.UnsafeFooTest$Simple");

        //Unsafe得到的实例不会调用类初始化
        Unsafe unsafe = getUnsafe();
        Simple simple = (Simple) unsafe.allocateInstance(Simple.class);
        System.out.println(simple.get());
    }

    static class Simple {
        private int i = 0;

        Simple() {
            i++;
            System.out.println("===Init===");
        }

        public int get() {
            return i;
        }
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
