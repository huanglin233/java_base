package com.hl.javase.thread.join;

/**
 * @author huanglin by 2021/5/15
 */
public class ThreadT3 extends Thread{

    @Override
    public void run() {
        try {
            ThreadT2 t2 = new ThreadT2();
            t2.start();
            t2.join();

            System.out.println("this is Thread T3 Do");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}