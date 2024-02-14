package com.hl.javase.thread.readWithWrite_;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author huanglin
 * @date 2023/08/03 23:18
 */
public class WriteThread extends Thread{

    ReentrantReadWriteLock rrwl;

    public WriteThread(String name, ReentrantReadWriteLock rrwl) {
        super(name);
        this.rrwl = rrwl;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " trying to lock");
        try {
            rrwl.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " lock successfully");
        } finally {
            rrwl.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " unlock successfully");
        }
    }
}

