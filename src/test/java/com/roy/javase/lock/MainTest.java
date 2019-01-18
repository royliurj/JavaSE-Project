package com.roy.javase.lock;

/**
 * @Author: Roy
 * @Date: 2019/1/18 9:55
 */
public class MainTest {

    public static void main(String[] args) {
//        Ticket ticket = new Ticket(20);
//        Thread window01 = new Thread(ticket, "窗口01");
//        Thread window02 = new Thread(ticket, "窗口02");
//        Thread window03 = new Thread(ticket, "窗口03");
//        window01.start();
//        window02.start();
//        window03.start();

        final DataUtil insertData = new DataUtil();
//
//        new Thread() {
//            public void run() {
//                insertData.insert(Thread.currentThread());
//            };
//        }.start();
//
//
//        new Thread() {
//            public void run() {
//                insertData.insert(Thread.currentThread());
//            };
//        }.start();

        new Thread(){
            @Override
            public void run() {
                insertData.insert2();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                insertData.insert3();
            }
        }.start();
    }
}
