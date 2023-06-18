package com.hl.javase.thread.test;

import java.util.List;

/**
 *
 * @author huanglin by 2021/5/15
 */
public class ReadList implements Runnable {
    volatile List<Integer> list;

    public ReadList(List<Integer> list) {
        this.list = list;
    }

    public ReadList() {}

    @Override
    public void run() {
        try {
            read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void read() throws InterruptedException {

        while(true) {
            if(list.size() > 0) {
                System.out.println("thead = " + Thread.currentThread().getId() + ", val = " + list.get(0));
                list.remove(0);
            } else {
                wait();
            }
        }
    }

    public synchronized void notifyRead() {
        notify();
    }
}