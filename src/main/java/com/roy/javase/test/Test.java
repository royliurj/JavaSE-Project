package com.roy.javase.test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @Author: Roy
 * @Date: 2019/5/13 9:58
 */
public class Test {
    private static int intStatic = 222;
    private static String strStatic = "old String";
    private static StringBuilder strBuilderStatic = new StringBuilder("old string builder");

    //dev
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()){
            String s = listIterator.next();
            System.out.println(s);
        }
        while (listIterator.hasPrevious()){
            String s = listIterator.previous();
            System.out.println(s);
        }

//        method(intStatic);
//        method(strStatic);
//        method(strBuilderStatic,strBuilderStatic);
//
//        System.out.println("a: " + intStatic);
//        method();
//
//        System.out.println("b: " + intStatic);
//
//        System.out.println(strStatic);
//
//        System.out.println(strBuilderStatic);
    }

    public static void method(int intStatic){
        intStatic = 777;
    }

    public static void method(){
        intStatic = 888;
    }

    public static void method(String strStatic){
        strStatic = "new String";
    }

    public static void method(StringBuilder strBuilderStatic, StringBuilder strBuilderStatic2){
        strBuilderStatic.append(".method.first-");
        strBuilderStatic2.append("method.second-");

        strBuilderStatic = new StringBuilder("new string builder");
        strBuilderStatic.append("new method's append");
    }
}
