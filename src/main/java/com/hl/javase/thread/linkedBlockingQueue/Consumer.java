package com.hl.javase.thread.linkedBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 * 
 * @author huanglin by 2021/5/15
 *
 */
public class Consumer implements Runnable{

    private final BlockingQueue<Integer> queue;
    private int                          nums = 20;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            System.out.println("开始消费数据");
            System.out.println("------------------------------");

            while(nums > 0) {
                nums--;
                while(true) {
                    int data = (int) queue.take();
                    Thread.sleep(500);
                    System.out.println("消费者消费的数据 : " + data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("消费者线程退出");
        }
    }
}