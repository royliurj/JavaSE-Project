package com.roy.javase.linq;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LinqApp {

//    //静态方法
//    public static String supplier1(){
//        return "supplier1";
//    }
//
//    public static void consumer1(Integer size){
//        System.out.println("size:" + size);
//    }
//
//    public static String toUppderCase(String str){
//        return str.toUpperCase();
//    }

//    public String put(){
//        return "Hello";
//    }
//
//    public String toUpper(String str){
//        return str.toUpperCase();
//    }
//
//    public void test(String str){
//        //this当前实例的方法，同理也可以使用super调用父类的方法
//        Function<String,String> fun = this::toUpper;
//        System.out.println(fun.apply(str));
//    }

//    private String str;
//
//    public String getStr() {
//        return str;
//    }
//
//    public void setStr(String str) {
//        this.str = str;
//    }
//
//    public String toUpper(LinqApp linqApp){
//        System.out.println("toUpper");
//        return "toUpper";
//    }
//
//    public void test(LinqApp app, String str){
//        System.out.println("test");
//    }

    public static void main(String[] args) {
//        //静态方法引用
//        Supplier<String> s = () -> LinqApp.supplier1();
//        Supplier<String> s2 = LinqApp::supplier1;
//
//        System.out.println(s.get());
//        System.out.println(s2.get());
//
//        Consumer<Integer> c = size -> LinqApp.consumer1(size);
//        Consumer<Integer> c2 = LinqApp::consumer1;
//
//        c.accept(100);
//        c2.accept(100);
//
//        Function<String,String> f = str -> str.toUpperCase();
//        Function<String,String> f2 = str -> LinqApp.toUppderCase(str);
//        Function<String,String> f3 = LinqApp::toUppderCase;
//
//        System.out.println(f.apply("abc"));
//        System.out.println(f2.apply("abc"));
//        System.out.println(f3.apply("abc"));

//        Supplier<String> s = () -> new LinqApp().put();
//        Supplier<String> s2 = () -> {return new LinqApp().put();};
//        Supplier<String> s3 = new LinqApp()::put;
//
//        System.out.println(s.get());
//        System.out.println(s2.get());
//        System.out.println(s3.get());
//
//        new LinqApp().test("aaa");

//        //对象方法引用
//        LinqApp app = new LinqApp();
//        app.setStr("aaa");
//
//        Function<LinqApp,String> fun = linqApp -> app.toUpper(linqApp);
//        Function<LinqApp,String> fun3 = app::toUpper;
//
//        System.out.println(fun.apply(app));
//        System.out.println(fun3.apply(app));
//
//        Consumer<LinqApp> c = (a) -> app.toUpper(a);
//        c.accept(app);
//
//        BiConsumer<LinqApp,String> c2 = (bc,str) -> app.test(bc,str);
//        BiConsumer<LinqApp,String> c3 = app::test;
//
//        c2.accept(app,"111");
//        c3.accept(app,"222");

//        //构造方法引用
//        //无参数
//        Supplier<Person> s = () -> new Person();
//        Supplier<Person> s2 = Person::new;
//
//        s.get();
//        s2.get();
//
//        //有参数
//        Consumer<Integer> c = (age) -> new Account(age);
//        Consumer<Integer> c2 = Account::new;
//
//        c.accept(10);
//        c2.accept(20);
    }
}

class Person{
    //无参构造函数
    public Person(){
        System.out.println("Person");
    }
}

class Account{
    public Account(Integer age){
        System.out.println("account");
    }
}
