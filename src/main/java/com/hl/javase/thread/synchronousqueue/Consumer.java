package com.hl.javase.thread.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author huanglin by 2021/5/15
 */
public class Consumer implements Runnable{

    SynchronousQueue<String> syncQueue;

    public Consumer(SynchronousQueue<String> syncQueue) {
        this.syncQueue = syncQueue;
    }

    @Override
    public void run() {
        String name;
        int    loop = 2;
        for(int i = 0; i < loop; i++) {
            try {
                name = syncQueue.take();
                System.out.println("take " + name);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}