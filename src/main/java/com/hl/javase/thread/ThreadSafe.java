package com.hl.javase.thread;


/**
 * @author huanglin by 2021/5/17
 */
public class ThreadSafe extends Thread{
    public volatile boolean exit = false;
    public int              i    = 0;

    @Override
    public void run() {
        while(!exit) {
            System.out.println("i = " + i++);
            if(i == 10) {
                exit = true;
            }
        }
    }
}