package com.roy.javase.concurrent.pattern.balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author: Roy
 * @Date: 2019/4/18 14:24
 */
public class Data {
    //文件名称
    private final String fileName;
    //修改内容
    private String content;
    //是否可以修改
    private boolean changed;
    //构造函数
    public Data(String fileName,String content){
        this.fileName=fileName;
        this.content=content;
    }
    /**
     * 新的修改内容 如果有新的需要保存的数据，changed的属性修改为true
     * 注:这个方法是用synchronized修饰的
     * 为了保护content与changed属性用
     * synchronized修饰后就只能同时有
     * 1个线程访问也就防止了多线程造成的脏数据
     * @param newContent
     */
    public synchronized void change(String newContent){
        content=newContent;
        changed=true;
    }
    /**
     * 保存
     */
    public synchronized void save(){
        //当changed值为false时直接return出去
        if(!changed){
            return ;
        }else{
            //调用保存方法
            doSave();
            //保存后changed的值修改为false
            changed=false;
        }
    }
    /**
     * 具体的修改方法，模拟写到文本中。
     */
    private void doSave(){
        System.out.println("线程名称:"+Thread.currentThread().getName()+",最新需要保存的值="+content);
        try {
            Writer writer=new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
