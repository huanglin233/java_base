package com.hl.javase.thread.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * @author huanglin
 * @date 2023/06/23 15:53
 */
public class VolatileTest {

    private static volatile boolean STOP = false;

    public static void main(String[] args) {
        // 新建线程
        new Thread("Thread A"){
            @Override
            public void run() {
                while(!STOP) {

                }
                System.out.println(Thread.currentThread() + " stopped");
            }
        }.start();

        // 主线程
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread() + " after 1 seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }

        STOP =  true;
    }
}
