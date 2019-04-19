package com.roy.javase.concurrent.pattern.twophasetermination;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 * @Author: Roy
 * @Date: 2019/4/19 13:50
 */
public class AppServer extends Thread {
    private final int port;
    private static int DEFULT_PORT = 17458;
    private volatile boolean start = true;
    private List<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
    private final static ExecutorService executor = Executors.newFixedThreadPool(5);
    private ServerSocket server;

    public AppServer(){
        this(DEFULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try{
            server = new ServerSocket(port);
            while (start){
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.execute(clientHandler);
                this.clientHandlers.add(clientHandler);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        } finally {
            //服务器端二阶段终止
            System.out.println("服务器二阶段终止，释放资源");
            this.dispose();
        }
    }

    /**
     * 二阶段终止方法
     */
    private void dispose() {
        this.clientHandlers.stream().forEach(ClientHandler::stop);
        executor.shutdown();
    }

    /**
     * 对外暴露的方法
     * @throws IOException
     */
    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        server.close();
    }
}
