package com.hl.javase.io.bio_;

import java.util.concurrent.CountDownLatch;

/**
 * @author huanglin
 * @date 2024/02/15 17:36
 */
public class SocketClientDoMain {

    public static void main(String[] args) throws InterruptedException {
        Integer        clientNumber = 20;
        CountDownLatch latch        = new CountDownLatch(clientNumber);

        // 分别开始启动这个20个客户端
        for(int index = 0; index < clientNumber; index++, latch.countDown()) {
            SocketClientRequestThread client = new SocketClientRequestThread(latch, index);
            new Thread(client).start();
        }

        // 保证守护线程在启动所有线程后,进入等待状态
        synchronized (SocketClientDoMain.class) {
            SocketClientDoMain.class.wait();
        }
    }
}