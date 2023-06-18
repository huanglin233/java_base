package com.hl.javase.thread.threadLocal;

/**
 * ThreadLocal测试主类
 *
 * @author huanglin by 2021/5/15
 */
public class DoMain {
    public static void main(String[] args) {
        ThreadDemo threadDemo1 = new ThreadDemo("threadDemo1");
        ThreadDemo threadDemo2 = new ThreadDemo("threadDemo2");;

        threadDemo1.start();
        threadDemo2.start();
    }
}