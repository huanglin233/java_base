package com.hl.javase.thread.lockSupport_;

import java.util.concurrent.locks.LockSupport;

/**
 * @author huanglin
 * @date 2023/07/19 23:24
 */
public class MainDo {

    public static void main(String[] args) {
        MainDo md = new MainDo();
//        md.waitAndNotify();
//        md.parkAndUnpark();
        md.threadInterrupt();
    }

    /**
     * 使用wait/notify实现线程同步
     */
    public void waitAndNotify() {
        Thread1 t1 = new Thread1();
        synchronized (t1) {
            try {
                t1.start();
                Thread.sleep(3000);
                System.out.println("before wait");
                t1.wait();
                System.out.println("after wait");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用park/unpark实现线程同步
     */
    public void parkAndUnpark() {
        Thread2 t2 = new Thread2(Thread.currentThread());
        t2.start();
        System.out.println("before park");
        // 获取许可
        LockSupport.park("ParkAndUnpark");
        System.out.println("after park");
    }

    public void threadInterrupt() {
        Thread3 t3 = new Thread3(Thread.currentThread());
        t3.start();
        System.out.println("before park");
        // 获取许可
        LockSupport.park(t3);
        System.out.println("after park");
    }
}
