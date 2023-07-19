package com.hl.javase.thread.lockSupport_;

/**
 * @author huanglin
 * @date 2023/07/19 23:21
 */
public class Thread1  extends Thread{

    @Override
    public void run() {
       synchronized(this) {
           System.out.println("before notify");
           notify();
           System.out.println("after notify");
       }
    }
}
