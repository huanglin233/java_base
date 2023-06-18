package com.hl.javase.thread.threadLocal;

/**
 * 测试线程
 *
 * @author huanglin by 2021/5/15
 */

public class ThreadDemo extends Thread{

    String threadName;

    ThreadLocal<String> localVal = new ThreadLocal<String>();

    public ThreadDemo(String threadName) {
        this.threadName = threadName;
    }

    public void print(String str) throws InterruptedException {
        // 打印当前线程中本地变量值
        System.out.println(str + " : " + localVal.get());
        Thread.sleep(1000 * 3);
        // 清楚本地内存中的本地变量
        localVal.remove();
    }

    @Override
    public void run() {
        // 设置线程1中本地变量的值
        localVal.set(threadName);
        // 打印线程类的方法
        try {
            print(threadName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印本地变量
        System.out.println(threadName + " after remove : " + localVal.get());
    }
}