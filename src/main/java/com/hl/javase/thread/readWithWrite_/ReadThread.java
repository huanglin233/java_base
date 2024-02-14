package com.hl.javase.thread.readWithWrite_;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author huanglin
 * @date 2023/08/03 23:14
 */
public class ReadThread extends Thread{

    private ReentrantReadWriteLock rrwl;

    public ReadThread(String name, ReentrantReadWriteLock rrwl) {
        super(name);
        this.rrwl = rrwl;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " trying to lock");
        try {
            rrwl.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " lock successfully");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rrwl.readLock().unlock();
        }
    }
}
