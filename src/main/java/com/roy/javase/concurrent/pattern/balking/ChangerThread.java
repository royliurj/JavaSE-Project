package com.roy.javase.concurrent.pattern.balking;

import java.util.Random;

/**
 * @Author: Roy
 * @Date: 2019/4/18 14:25
 */
public class ChangerThread extends Thread{
    private Data data;
    private Random random=new Random();
    public ChangerThread(String name,Data data){
        super(name);
        this.data=data;
    }
    @Override
    public void run(){
        try {
            for(int i=0;true;i++){
                //模拟的数据
                data.change("No:"+i);
                //模拟去做别的事情
                Thread.sleep(random.nextInt(1000));
                //明确的要求要保存
                data.save();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

