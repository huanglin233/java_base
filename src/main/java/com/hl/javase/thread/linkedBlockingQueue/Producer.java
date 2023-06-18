package com.hl.javase.thread.linkedBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 * 
 * @author huanglin by 2021/5/15
 *
 */
public class Producer implements Runnable{

    private final BlockingQueue<Integer>  queue;
    private int                           nums  = 20;
    private static volatile AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始产生数据");
            System.out.println("------------------------------");

            while(true) {
                nums--;
                int decrementAndGet = count.decrementAndGet();
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + " - " + Thread.currentThread().getId() + ": 产生了数据 =>" + "decrementAndGet = " + decrementAndGet);
                queue.put(decrementAndGet);
            } 
        }catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
        } finally {
                System.out.println("生产线程退出");
        }
    }
}
