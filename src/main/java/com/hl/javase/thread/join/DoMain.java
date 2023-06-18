package com.hl.javase.thread.join;

/**
 * Join测试类
 *
 * @author huanglin by 2021/5/15
 */
public class DoMain {

    public static void main(String[] args) throws InterruptedException {
        ThreadT3 t3 = new ThreadT3();
        t3.start();
        t3.join();

        System.out.println("This is Main do");
    }
}