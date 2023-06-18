package com.hl.javase.thread.arrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消费者
 * 
 * @author huanglin by 2021/5/15
 *
 */
public class Consumer implements Runnable{

    private final ArrayBlockingQueue<Breiad> queue;

    public Consumer(ArrayBlockingQueue<Breiad> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * 5 * 20);
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumer() {
        try {
            Breiad breiad = queue.take();
            System.out.println("Consumer <- " + breiad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}