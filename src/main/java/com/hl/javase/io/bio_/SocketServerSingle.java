package com.hl.javase.io.bio_;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author huanglin
 * @date 2024/02/15 17:42
 */
public class SocketServerSingle {

    static {
        BasicConfigurator.configure();
    }

    private static final Log LOGGER = LogFactory.getLog(SocketServerSingle.class);

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(83);

        try {
            while(true) {
                Socket socket = serverSocket.accept();

                // 接受消息
                InputStream  in           = socket.getInputStream();
                OutputStream out          = socket.getOutputStream();
                Integer      sourcePort   = socket.getPort();
                int          maxLen       = 2048;
                byte[]       contextBytes = new byte[maxLen];

                // 阻塞知道接受到消息
                int    realLen = in.read(contextBytes, 0, maxLen);
                String message = new String(contextBytes, 0, realLen);
                SocketServerSingle.LOGGER.info("服务器收到来自于客户端: " + sourcePort + "的信息: " + message);

                // 回复发送消息
                out.write("回发响应消息！".getBytes());

                // 关闭连接
                out.close();
                in.close();
                socket.close();
            }
        } catch (Exception e) {
            SocketServerSingle.LOGGER.error(e.getMessage(), e);
        } finally {
            if(serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
