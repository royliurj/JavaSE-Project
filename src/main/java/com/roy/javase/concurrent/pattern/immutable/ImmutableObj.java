package com.roy.javase.concurrent.pattern.immutable;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Roy
 * @Date: 2019/3/19 9:33
 */
final public class ImmutableObj {
    private final String name;
    private final int age;
    private final List<String> interestList;

    public ImmutableObj(String name, int age, List<String> interestList) {
        this.name = name;
        this.age = age;
        this.interestList = interestList;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * 因为this是不可变对象，所以内部的引用类型，应该返回副本或不允许修改
     * @return
     */
    public List<String> getInterestList() {
        return Collections.unmodifiableList(interestList);
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", interestList=" + interestList +
                '}';
    }
}
