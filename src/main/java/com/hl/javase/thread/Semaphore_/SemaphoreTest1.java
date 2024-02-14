package com.hl.javase.thread.Semaphore_;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglin
 */
public class SemaphoreTest1 extends Thread{
    public final static int SEM_SIZE = 10;
    
    private Semaphore semaphore;

    public SemaphoreTest1(String name, Semaphore semaphore) {
        super(name);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int count = 3;
        System.out.println(Thread.currentThread().getName() + " trying to acquire");
        try {
            semaphore.acquire(count);
            System.out.println(Thread.currentThread().getName() + " acquire successfully");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(count);
            System.out.println(Thread.currentThread().getName() + " release successfully");
        }
    }

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(SEM_SIZE);
        SemaphoreTest1 t1 = new SemaphoreTest1("t1", semaphore);
        SemaphoreTest1 t2 = new SemaphoreTest1("t2", semaphore);
        t1.start();
        t2.start();
        int permits = 5;
        System.out.println(Thread.currentThread().getName() + " trying to acquire");
        try {
            semaphore.acquire(permits);
            System.out.println(Thread.currentThread().getName() + " acquire successfully");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " release successfully");
        }

        // 一个线程调用1次release方法,release会添加令牌，并不会以初始化的大小为准
        int permitsNum = 2;
        Semaphore semaphore2 = new Semaphore(permitsNum);
        System.out.println("令牌数：" + semaphore2.availablePermits() + ", semaphore.tryAcquire(3, 1, TimeUnit.SECONDS):" + semaphore.tryAcquire(3, 1, TimeUnit.SECONDS));
        semaphore2.release();
        System.out.println("令牌数：" + semaphore2.availablePermits() + ", semaphore.tryAcquire(3, 1, TimeUnit.SECONDS):" + semaphore.tryAcquire(3, 1, TimeUnit.SECONDS));
    }
}
