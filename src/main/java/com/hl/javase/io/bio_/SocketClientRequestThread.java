package com.hl.javase.io.bio_;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author huanglin
 * @date 2024/02/15 16:58
 */
public class SocketClientRequestThread implements Runnable{

    static {
        BasicConfigurator.configure();
    }

    private static final Log LOGGER = LogFactory.getLog(SocketClientRequestThread.class);

    /**
     * countDownLatch 计数器
     * 当计数器数值减为0时,所有受其影响而等待的线程将会被激活.这样保证模拟并发请求的真实性
     */
    private CountDownLatch latch;

    /**
     * 这个线程的编号
     */
    private Integer clientIndex;

    public SocketClientRequestThread(CountDownLatch latch, Integer clientIndex) {
         this.latch       = latch;
         this.clientIndex = clientIndex;
    }

    @Override
    public void run() {
        Socket       socket         = null;
        OutputStream clientRequest  = null;
        InputStream  clientResponse = null;

        try {
            socket         = new Socket("localhost", 83);
            clientRequest  = socket.getOutputStream();
            clientResponse = socket.getInputStream();

            // 等待,直到SocketClientRequestThread完成所有线程的启动,然后所有线程一起发送请求
            this.latch.await();

            // 发送请求
            clientRequest.write(("这是第" + this.clientIndex + "个客户端的请求").getBytes());
            clientRequest.flush();

            // 接受服务器响应
            SocketClientRequestThread.LOGGER.info("第" + this.clientIndex + "个客户端的响应是:");
            int    maxLen       = 1024;
            byte[] contextBytes = new byte[maxLen];
            int    realLen;
            String message      = "";
            // 阻塞等待服务器返回信息
            while((realLen = clientResponse.read(contextBytes, 0, maxLen)) != -1) {
                message += new String(contextBytes, 0, realLen);
            }
            SocketClientRequestThread.LOGGER.info("接受响应的信息: " + message);
        } catch (Exception e) {
            SocketClientRequestThread.LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if(clientRequest != null) {
                    clientRequest.close();
                }
                if(clientResponse != null) {
                    clientResponse.close();
                }
                if(socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                SocketClientRequestThread.LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
