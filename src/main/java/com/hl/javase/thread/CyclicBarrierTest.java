package com.hl.javase.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @author huanglin
 * @date 2021/7/3 下午12:27
 */
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier;

    static class CyclicBarrierThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了");
            try{
                Thread.sleep(2000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "离场");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("人到齐了， 开会吧....");
            }
        });

        for(int i = 0; i < 4; i++) {
            new CyclicBarrierThread().start();
        }

        System.out.println("thread start all");
    }
}