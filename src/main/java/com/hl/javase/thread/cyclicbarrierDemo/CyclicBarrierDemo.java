package com.hl.javase.thread.cyclicbarrierDemo;

import java.util.concurrent.CyclicBarrier;

/**
 * @author huanglin by 2021/5/15
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int threadCount = 5;
        CyclicBarrier  barrier = new CyclicBarrier(threadCount - 2, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "完成最后任务");
            }
        });
        
        for (int i = 0; i < threadCount; i++) {
            new TaskThread(barrier).start();
        }
    }
}