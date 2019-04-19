package com.roy.javase.concurrent.pattern.twophasetermination;

import java.io.*;
import java.net.Socket;

/**
 * @Author: Roy
 * @Date: 2019/4/19 13:56
 */
public class ClientHandler implements Runnable {
    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(outputStream)){

            while (running){
                String message = br.readLine();
                if(null == message){
                    break;
                }
                System.out.println("Come from client > " + message);
                printWriter.write("echo " + message + "\n");
                printWriter.flush();
            }

        } catch (IOException e){
            this.running = false;
        } finally {
            //二阶段终止
            System.out.println("客户端二阶段终止，释放资源");
            this.stop();
        }
    }

    /**
     * 二阶段终止
     */
    public void stop(){
        //Balking模式
        if(!running){
            return;
        }

        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
