package com.hl.javase.thread.synchronized_;

/**
 * @author huanglin
 * @date 2023/06/18 23:26
 */
public class SynchronizedObjectLock_1 implements Runnable{
    static SynchronizedObjectLock_1 instance = new SynchronizedObjectLock_1();

    // 创建两把锁
    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {
        // 这个代码块使用的是第一把锁, 当他释放后, 后面的代码由于使用的第二把锁, 所以可以马上执行
        synchronized (lock1) {
            System.out.println("lock1锁: " + Thread.currentThread().getName() + "开始");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("lock1锁: " + Thread.currentThread().getName() + "结束");
        }

        synchronized (lock2) {
            System.out.println("lock2锁: " + Thread.currentThread().getName() + "开始");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("lock2锁: " + Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();
    }
}
