package com.hl.javase;

import java.util.concurrent.Semaphore;

/**
 * 懒汉式，线程安全
 *
 * @author huanglin by 2021/5/17
 */
public class Singleton2 {
    private static Singleton2 singleton;

    private Singleton2() {}

    public static synchronized Singleton2 getInstance() {
        if(singleton == null) {
            singleton = new Singleton2();
        }

        return singleton;
    }

    /**
     * @author huanglin by 2021/5/17
     */
    public static class SemaphoreTest extends Thread {

        /**
         * 信号量
         */
        private static final Semaphore SEMAPHORE = new Semaphore(2);

        @Override
        public void run() {
            test();
        }

        public void test() {
            try {
                // 申请许可
                SEMAPHORE.acquire();
                try {
                    System.out.println(Thread.currentThread().getName() + "acquire");
                    Thread.sleep(1000 * 5);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放许可
                    SEMAPHORE.release();
                    System.out.println(Thread.currentThread().getName() + "release");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            int forNum = 20;
            for(int i = 0 ; i < forNum; i++) {
                SemaphoreTest semaphoreTest = new SemaphoreTest();
                semaphoreTest.start();
            }
        }
    }
}