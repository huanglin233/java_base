package com.hl.javase.thread.synchronized_;

/**
 * synchronized指定锁为Class对象
 * @author huanglin
 * @date 2023/06/20 20:48
 */
public class SynchronizedObjectLock_4 implements Runnable{

    static SynchronizedObjectLock_4 instance1 = new SynchronizedObjectLock_4();
    static SynchronizedObjectLock_4 instance2 = new SynchronizedObjectLock_4();

    @Override
    public void run() {
        // 所有线程需要得锁都是同一把
        synchronized (SynchronizedObjectLock_4.class) {
            System.out.println("线程: " + Thread.currentThread().getName() + " 开始");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程: " + Thread.currentThread().getName() + " 结束");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
    }
}
