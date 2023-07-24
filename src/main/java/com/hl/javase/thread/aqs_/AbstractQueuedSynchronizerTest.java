package com.hl.javase.thread.aqs_;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huanglin
 * @date 2023/07/20 23:13
 */
public class AbstractQueuedSynchronizerTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread1 t1 = new Thread1("t1", lock);
        Thread1 t2 = new Thread1("t2", lock);

        t1.start();
        t2.start();
    }
}
