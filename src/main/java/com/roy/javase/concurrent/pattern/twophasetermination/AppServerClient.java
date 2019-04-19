package com.roy.javase.concurrent.pattern.twophasetermination;

import java.io.IOException;

/**
 * @Author: Roy
 * @Date: 2019/4/19 14:10
 */
public class AppServerClient {
    public static void main(String[] args) {
        AppServer server = new AppServer(12345);
        server.start();

        try {
            Thread.sleep(30_000L);
            server.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
