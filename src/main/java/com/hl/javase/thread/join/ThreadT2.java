package com.hl.javase.thread.join;

/**
 * @author huanglin233 by 2021/5/15
 */
public class ThreadT2 extends Thread{

    @Override
    public void run() {
        try {
            ThreadT1 t1 = new ThreadT1();
            t1.start();
            t1.join();

            System.out.println("this is Thread T2 Do");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
