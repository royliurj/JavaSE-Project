package com.roy.javase.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Roy
 * @Date: 2019/5/5 10:54
 */
public class Main {
    public static void main(String[] args) {
        DiskClassLoader diskLoader = new DiskClassLoader("D:\\lib");
        try {
            //加载class文件
            Class c = diskLoader.loadClass("com.roy.javase.classloader.Test");

            System.out.println(c.getClassLoader());
            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say",null);
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//
////        ClassLoader cl = Test.class.getClassLoader();
////
////        System.out.println("ClassLoader is:"+cl.toString());
////        System.out.println("ClassLoader\'s parent is:"+cl.getParent().toString());
////        System.out.println("ClassLoader\'s grand father is:"+cl.getParent().getParent().toString());
//
//
//
////        System.out.println("BootstrapClassLoader: ");
////        System.out.println(System.getProperty("sun.boot.class.path"));
////        System.out.println("==========================");
////        System.out.println("ExtClassLoader");
////        System.out.println(System.getProperty("java.ext.dirs"));
////        System.out.println("==========================");
////        System.out.println("AppClassLoader");
////        System.out.println(System.getProperty("java.class.path"));
    }
}
