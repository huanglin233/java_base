package com.hl.javase.io;

import java.net.Socket;
import java.util.Date;

/**
 * bio 客户端
 *
 * @author huanglin by 2021/5/15
 */
public class IoClient {

    /**
     * 创建多个线程,模拟多个客户端连接服务端
     */
    public static void main(String[] args) {
        int threadCount = 10;

        for(int i = 0; i < threadCount; i++) {
            ThreadClientHandler threadTest = new ThreadClientHandler();
            threadTest.start();
        }
    }

}

class ThreadClientHandler extends Thread {

    @Override
    public void run() {
        try{
            Socket socket = new Socket("127.0.0.1", 3333);
            try {
                System.out.println("client to msg");
                socket.getOutputStream().write((new Date() + " : hello word").getBytes());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}