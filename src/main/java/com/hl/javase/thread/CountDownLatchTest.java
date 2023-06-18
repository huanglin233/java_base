package com.hl.javase.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author huanglin
 * @date 2021/7/3 下午1:37
 */
public class CountDownLatchTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    /**
     * Boss thread, wait employ;
     */
    static class BossThread extends Thread {
        @Override
        public void run() {
            System.out.println("boss 在会议室等待，总共有" + countDownLatch.getCount() + "个人在开发会");
            try {
                // boss 等待
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("所有人都已经到齐了，开会吧...");
        }
    }

    // 员工到达会议室
    static class EmployeeThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", 到达会议室...");
            // 员工到达会议室 count - 1
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        new BossThread().start();
        for(int i = 0; i < 5; i++) {
            new EmployeeThread().start();
        }
    }
}