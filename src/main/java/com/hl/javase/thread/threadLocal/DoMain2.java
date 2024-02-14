package com.hl.javase.thread.threadLocal;

/**
 * @author huanglin
 * @date 2024/02/14 19:48
 */
public class DoMain2 {

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        Thread          t1   = new Thread(test, "Thread-A");
        Thread          t2   = new Thread(test, "Thread-B");
        t1.start();
        t2.start();
    }
}
