package com.hl.javase.thread;

/**
 * @author huanglin by 2021/5/15
 */
public class DeadLockDemo {
    private static final Object RESOURCE1 = new Object();
    private static final Object RESOURCE2 = new Object();

    public static void main(String[] args) {
        CompetingThread1 thread1 = new CompetingThread1(RESOURCE1, RESOURCE2);
        CompetingThread2 thread2 = new CompetingThread2(RESOURCE1, RESOURCE2);

        thread1.start();
        thread2.start();
    }
}

class CompetingThread1 extends Thread{

    Object resource1;
    Object resource2;

    public CompetingThread1(Object resource1, Object resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        synchronized (resource1) {
            System.out.println(Thread.currentThread() + " get resource1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "wating get resource2");
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + " get resource2");
            }
        }
    }
}


class CompetingThread2 extends Thread{

    Object resource1;
    Object resource2;

    public CompetingThread2(Object resource1, Object resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        synchronized (resource2) {
            System.out.println(Thread.currentThread() + " get resource2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "wating get resource1");
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + " get resource1");
            }
        }
    }
}