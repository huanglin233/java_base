package com.hl.javase.thread.cyclicbarrierDemo;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author huanglin by 2021/5/15
 */
public class TaskThread extends Thread{

    CyclicBarrier cyclicBarrier;

    public TaskThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(getName() + "到达栅栏A");
            cyclicBarrier.await();
            System.out.println(getName() + "冲破栅栏A");


            Thread.sleep(2000);
            System.out.println(getName() + "到达栅栏B");
            cyclicBarrier.await();
            System.out.println(getName() + "冲破栅栏B");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
