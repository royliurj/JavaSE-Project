package com.roy.javase.lock;

import java.util.ArrayList;

/**
 * @Author: Roy
 * @Date: 2019/1/18 10:38
 */
public class DataUtil {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public synchronized void insert(Thread thread){
        for(int i=0;i<10;i++){
            System.out.println(thread.getName()+"在插入数据"+i);
            arrayList.add(i);
        }
    }

    public synchronized void insert2(){
        System.out.println("执行insert2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行insert2完毕");
    }

    public synchronized static void insert3(){

        System.out.println("执行insert3");
        System.out.println("执行insert3完毕");
    }
}
