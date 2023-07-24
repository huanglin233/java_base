package com.hl.javase.thread.aqs_;

import java.util.concurrent.locks.Lock;

/**
 * @author huanglin
 * @date 2023/07/20 22:32
 */
public class Thread1 extends Thread{

    private Lock lock;

    public Thread1(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
        } finally {
            lock.unlock();
        }
    }
}
