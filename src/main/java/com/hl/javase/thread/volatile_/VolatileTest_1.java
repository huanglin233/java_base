package com.hl.javase.thread.volatile_;

/**
 * i++ 在多线程条件下,不满足原子性
 * @author huanglin
 * @date 2023/06/24 15:50
 */
public class VolatileTest_1 {

    volatile int i;

    public void addI() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest_1 t1 = new VolatileTest_1();
        for(int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                        t1.addI();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(10000);
        System.out.println(t1.i);
    }
}
