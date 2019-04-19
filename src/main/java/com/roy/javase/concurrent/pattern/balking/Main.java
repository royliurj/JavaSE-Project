package com.roy.javase.concurrent.pattern.balking;

/**
 * @Author: Roy
 * @Date: 2019/4/18 14:25
 */
public class Main {
    public static void main(String[] args) {
        //文件的名称及文件的默认内容
        Data data=new Data("data.txt", "我是默认内容");
        //手动保存
        new ChangerThread("ChangeThread", data).start();
        //自动保存
        new ServerThread("SaveThread", data).start();
    }
}
