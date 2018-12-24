package com.roy.javase.linq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApp {

    //通过数组创建Stream
    static void gen1(){
        String[] arr = {"a","b","c","d"};
        Stream<String> stream = Stream.of(arr);

        stream.forEach(s -> System.out.println(s));
    }
    //通过集合创建stream
    static void gen2(){
        List<String> list = Arrays.asList("a","b","c","d");
        Stream<String> stream = list.stream();
        stream.forEach(s -> System.out.println(s));
    }

    //通过Stream.generate创建
    //参数：Supplier<T>
    static void gen3(){
        Stream<Integer> stream = Stream.generate(() -> 1);
        stream.limit(10).forEach(s -> System.out.println(s));
    }

    //通过Stream.iterate创建
    //参数，起始值，Function<T,T>
    static void gen4(){
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
        stream.limit(10).forEach(s -> System.out.println(s));
    }

    //其它Api创建
    static void gen5() throws IOException {
        String str = "asdf1234";
        //创建一个IntStream
        IntStream stream = str.chars();

        stream.forEach(x -> System.out.println(x));

        Stream<String> stream1 = Files.lines(Paths.get("d:/abc.java"));
        stream1.forEach(line -> System.out.println(line));
    }

    public static void main(String[] args) throws IOException {
//        gen1();
//        gen2();
//        gen3();
//        gen4();
//        gen5();

//        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream();
//        stream = stream.filter(s -> {
//            return s % 2 == 0;
//        });

//        Optional<Integer> any = stream.findAny();
//        System.out.println(any.get());

//        Optional<Integer> first = stream.sorted((a,b) -> b-a).findFirst();
//        System.out.println(first.get());

//        System.out.println(stream.max((a,b) -> a-b).get());

//        System.out.println(stream.mapToInt(x -> x).sum());
//        System.out.println("偶数：" + stream.count());

//        Arrays.asList("adc","ro","adfd","a").stream().sorted((a,b) -> a.length() - b.length()).forEach(s -> System.out.println(s));
//        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
//        stream = stream.limit(50);
//        stream = stream.filter(s -> s % 2 == 0);
//
//
//        List<Integer> collect = stream.filter(s -> s % 2 == 0).collect(Collectors.toList());
//        for (Integer i :
//                collect) {
//            System.out.println(i);
//        }

//        String str = "11,22,33";
//        Stream<String> stream = Stream.of(str.split(","));
//        Stream<Integer> integerStream = stream.map(s -> Integer.parseInt(s));
//        System.out.println(integerStream.mapToInt(s -> s).sum());

//        String str = "tomcat,nginx,apache,jeety";
//        Stream<String> stream = Stream.of(str.split(","));
//////        stream.map(s -> new User(s)).forEach(s -> System.out.println(s));
////        stream.map(User::new).forEach(s -> System.out.println(s));
//
//        stream.peek(s -> System.out.println("peek-" + s)).forEach(s -> System.out.println(s));

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","5");

        Optional<Integer> optional = Stream.iterate(1, x -> x + 1).limit(200).peek(x -> {
            System.out.println(Thread.currentThread().getName());
        }).parallel().max(Integer::compareTo);
        System.out.println(optional.get());

    }

}

class User{
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}