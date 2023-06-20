package com.hl.javase.thread.synchronized_;

/**
 * synchronized修饰静态方法
 * @author huanglin
 * @date 2023/06/19 00:17
 */
public class SynchronizedObjectLock_2 implements Runnable{

    static SynchronizedObjectLock_2 instance1 = new SynchronizedObjectLock_2();
    static SynchronizedObjectLock_2 instance2 = new SynchronizedObjectLock_2();

    @Override
    public void run() {
        method();
    }

    // synchronized用在普通方法上, 默认的锁就是this, 当前实列
    public synchronized void method() {
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
        // t1和t2对应的this是两个不同的实列, 所以代码不会串行
        t1.start();
        t2.start();
    }
}
