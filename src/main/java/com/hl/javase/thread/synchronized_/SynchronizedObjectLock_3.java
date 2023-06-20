package com.hl.javase.thread.synchronized_;

/**
 * synchronized修饰静态方法
 * @author huanglin
 * @date 2023/06/19 00:17
 */
public class SynchronizedObjectLock_3 implements Runnable{

    static SynchronizedObjectLock_3 instance1 = new SynchronizedObjectLock_3();
    static SynchronizedObjectLock_3 instance2 = new SynchronizedObjectLock_3();

    @Override
    public void run() {
        method();
    }

    // synchronized用在静态方法上, 默认的锁就是当前所在的class类, 所以无论是哪个线程访问它, 需要的锁都只有一把
    public static synchronized void method() {
        System.out.println("线程: " + Thread.currentThread().getName() + " 开始");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("线程: " +Thread.currentThread().getId() + " 结束");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
    }
}
