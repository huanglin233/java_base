package com.hl.javase.thread;

/**
 * @author huanglin by 2021/5/17
 */
public class ThreadSafeTest {
    public static void main(String[] args) {
        ThreadSafe threadSafe = new ThreadSafe();
        threadSafe.start();
    }
}