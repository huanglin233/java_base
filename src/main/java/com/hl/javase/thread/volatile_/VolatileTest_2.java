package com.hl.javase.thread.volatile_;

/**
 * volatile的有序性
 * @author huanglin
 * @date 2023/06/24 16:20
 */
public class VolatileTest_2 {

    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 1;
        flag = true;
    }

    public void reader() {
        while(!flag) {
        }
        int i = a;
        System.out.println(i);
    }

    public static void main(String[] args) {
        VolatileTest_2 t2 = new VolatileTest_2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t2.reader();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t2.writer();
            }
        }).start();

    }
}
