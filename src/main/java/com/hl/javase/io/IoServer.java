package com.hl.javase.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio 服务端
 *
 * @author huanglin by 2021/5/15
 */
public class IoServer {
    public static void main(String[] args) throws IOException {
/*        try (ServerSocket serverSocket = new ServerSocket(3333)) {
            new Thread(() -> {
               while(true) {
                   // 阻塞方法获取新的链接
                   try (Socket accept = serverSocket.accept()) {
                       // 每一个连接都创建一个线程,负责读取数据
                       new Thread(() -> {
                          try {
                              int len;
                              byte[] data = new byte[1024];
                              InputStream inputStream = accept.getInputStream();
                              // 按字节流方式读取数据
                              while((len = inputStream.read(data)) != -1) {
                                  System.out.println(new String(data, 0, len));
                              }
                          }catch (IOException e) {
                              e.printStackTrace();
                          }
                       });
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        ServerSocket serverSocket = new ServerSocket(3333);

        // 接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理
        while (true) {
            try {
                // 阻塞方法获取新的连接
                System.out.println("server await msg");
                Socket socket = serverSocket.accept();
                System.out.println("server start handler msg");
                // 每一个新的连接都创建一个线程，负责读取数据
                ThreadServerHandler handler = new ThreadServerHandler(socket);
                handler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadServerHandler extends Thread {

    public Socket socket;

    public ThreadServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            int len;
            byte[] data = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            // 按字节流方式读取数据
            while ((len = inputStream.read(data)) != -1) {
                System.out.println(new String(data, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}