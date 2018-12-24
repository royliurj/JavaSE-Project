package com.roy.javase;

import com.roy.javase.linq.model.Book;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {

    @Test
    public void test(){
        Stream.iterate(1,x ->x +1).limit(10).forEach(x -> System.out.println(x));
    }

    @Test
    public void testMap(){
        String query="itemId=1&userName=roy&a=1";
        Stream<String> stream = Stream.of(query.split("&"));
        Stream<String[]> arrStream = stream.map(str -> str.split("="));
        Map<String, String> map = arrStream.collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println(map);

        //合并
        //Map<String, String> collect = Stream.of(query.split("&")).map(str -> str.split("=")).collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }

    private List<Book> books(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"java",80));
        books.add(new Book(2,"mysql",80));
        books.add(new Book(3,"C#",70));

        return books;
    }

    @Test
    public void test2(){
        //获取对象ID的集合：List<Book> -> List<Integer>
        List<Book> books = books();
        List<Integer> ids = books.stream().map(book -> book.getId()).collect(Collectors.toList());
        System.out.println(ids);

        //List<Integer> -> 字符串1,2,3
        String s = books.stream().map(book -> book.getId() + "").collect(Collectors.joining(","));
        System.out.println(s);

        //List<Integer> -> 字符串(1,2,3)
        String s2 = books.stream().map(book -> book.getId() + "").collect(Collectors.joining(",","(",")"));
        System.out.println(s2);
    }

    @Test
    public void test3(){
        String str = "11,22,33,11,22";
        Set<String> set = Stream.of(str.split(",")).collect(Collectors.toSet());
        System.out.println(set);
    }

    @Test
    public void test4(){
        //从低到高排序
        //books().stream().sorted((b1,b2) -> Double.compare(b1.getPrice(),b2.getPrice())).forEach(book -> System.out.println(book));

        //从高到底排序
//        Comparator<Book> comparator = (b1,b2) -> Double.compare(b1.getPrice(),b2.getPrice());
//        books().stream().sorted(comparator.reversed()).forEach(book -> System.out.println(book));


        //先按照价格低到高排序，相同价格的在按照ID高到低排序
//        Comparator<Book> comparator = (b1,b2) -> Double.compare(b1.getPrice(),b2.getPrice());
//        Comparator<Book> comparator1  = (b1,b2) -> Integer.compare(b1.getId(),b2.getId());
//        books().stream().sorted(comparator.thenComparing(comparator1.reversed())).forEach(book -> System.out.println(book));

//        books().stream().sorted(Comparator.comparing(book -> book.getPrice())).forEach(book -> System.out.println(book));

//        books().stream().sorted(Comparator.comparing(Book::getPrice).reversed()).forEach(book -> System.out.println(book));
        books().stream().sorted(Comparator.comparing(Book::getPrice).thenComparing(Comparator.comparing(Book::getId).reversed())).forEach(book -> System.out.println(book));
    }

    @Test
    public void test5(){
        Map<Integer, Book> map = books().stream().collect(Collectors.toMap(book -> book.getId(), book -> book));
        System.out.println(map);
    }

    @Test
    public void test6(){
        Double aDouble = books().stream().collect(Collectors.averagingDouble(Book::getPrice));
        System.out.println(aDouble);

    }

    @Test
    public void test7(){
        Optional<Book> optional = books().stream().max(Comparator.comparing(book -> book.getPrice()));
        System.out.println(optional.get());

        Optional<Book> bookOptional = books().stream().collect(Collectors.minBy(Comparator.comparing(Book::getPrice)));
        System.out.println(bookOptional.get());
    }

    @Test
    public void test8(){
        Map<Double, List<Book>> map = books().stream().collect(Collectors.groupingBy(Book::getPrice));

        for (Map.Entry<Double, List<Book>> entry: map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }


        Map<Double, Long> collect = books().stream().collect(Collectors.groupingBy(Book::getPrice, Collectors.counting()));
        System.out.println(collect);


        Map<Double, Double> collect1 = books().stream().collect(Collectors.groupingBy(Book::getPrice, Collectors.summingDouble(Book::getPrice)));
        System.out.println(collect1);
    }
}
