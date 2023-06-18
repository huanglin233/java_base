package com.hl.javase.thread.synchronousqueue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin by 2021/5/15
 */
public class Producer implements Runnable{

    SynchronousQueue<String> syncQueue;
    static int cnt = 0;
    
    public Producer(SynchronousQueue<String> syncQueue) {
        this.syncQueue = syncQueue;
    }

    @Override
    public void run() {
        String name = "";
        int    val  = 0;
        int    loop = 2;
        Random random = new Random(System.currentTimeMillis());

        for(int i = 0; i < loop; i++) {
            cnt = (cnt + 1);
            try {
                val = random.nextInt() % 15;
                if(val < 5) {
                    name = "off name: " + cnt;
                    syncQueue.offer(name);
                } else {
                    name = "off wait time and name : " + cnt;
                    boolean offer = syncQueue.offer(name, 1000, TimeUnit.MILLISECONDS);
                    System.out.println("off wait time and name : " + cnt + " => offer = " + offer);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}