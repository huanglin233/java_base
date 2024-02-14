package com.hl.javase.thread.readWithWrite_;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author huanglin
 * @date 2023/08/03 23:25
 */
public class MainDo {

    public static void main(String[] args) {
        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
        ReadThread rt1 = new ReadThread("rt1", rrwl);
        ReadThread rt2 = new ReadThread("rt2", rrwl);
        WriteThread wt1 = new WriteThread("wt1", rrwl);
        rt1.start();
        rt2.start();
        wt1.start();
    }
}
