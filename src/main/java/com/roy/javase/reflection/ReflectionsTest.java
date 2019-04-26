package com.roy.javase.reflection;

import com.roy.javase.concurrent.lock.Lock;
import org.reflections.Reflections;

import java.util.Set;

/**
 * @Author: Roy
 * @Date: 2019/4/26 15:56
 */
public class ReflectionsTest {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.roy.javase.concurrent");

        System.out.println("==============");
        Set<Class<? extends Lock>> classes = reflections.getSubTypesOf(Lock.class);
        for(Class clazz : classes) {
            System.out.println("Found: " + clazz.getName());
        }
    }
}
