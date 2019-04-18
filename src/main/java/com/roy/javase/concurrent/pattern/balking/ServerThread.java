package com.roy.javase.concurrent.pattern.balking;

/**
 * 定期保存的类
 * @Author: Roy
 * @Date: 2019/4/18 14:25
 */
public class ServerThread extends Thread{
    private Data data;

    public ServerThread(String name,Data data){
        super(name);
        this.data=data;
    }

    @Override
    public void run(){
        try {
            while(true){
                //模拟保存方法
                data.save();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
