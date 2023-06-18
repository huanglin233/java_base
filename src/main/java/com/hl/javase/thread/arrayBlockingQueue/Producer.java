package com.hl.javase.thread.arrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 生产者
 * 
 * @author huanglin by 2021/5/15
 *
 */
public class Producer implements Runnable{

    private final ArrayBlockingQueue<Breiad> queue;

    public Producer(ArrayBlockingQueue<Breiad> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000 * 5);
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void produce() {
        try {
            Breiad breiad = new Breiad();
            queue.put(breiad);
            System.out.println("Producer -> " + breiad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}