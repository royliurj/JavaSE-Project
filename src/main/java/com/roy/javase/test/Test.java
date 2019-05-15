package com.roy.javase.test;

/**
 * @Author: Roy
 * @Date: 2019/5/13 9:58
 */
public class Test {
    private static int intStatic = 222;
    private static String strStatic = "old String";
    private static StringBuilder strBuilderStatic = new StringBuilder("old string builder");

    public static void main(String[] args) {
        method(intStatic);
        method(strStatic);
        method(strBuilderStatic,strBuilderStatic);

        System.out.println("a: " + intStatic);
        method();

        System.out.println("b: " + intStatic);

        System.out.println(strStatic);

        System.out.println(strBuilderStatic);
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
