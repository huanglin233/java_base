package com.hl.javase.thread;

import java.util.concurrent.Semaphore;

/**
 * @author huanglin
 * @date 2017/7/3 15:49
 */
public class SemaphoreTest {

    static class Parking {
        // 型号量
        private Semaphore semaphore;

        public Parking(int count) {
            semaphore = new Semaphore(count);
        }

        public void park() {
            try {
                // 获取信号量
                semaphore.acquire();
                long time = (long)(Math.random() * 10);
                System.out.println(Thread.currentThread().getName() + "进入停车场， 停车" + time + "秒");
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "开出停车场...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    static class Car extends Thread {
        Parking parking;

        public Car(Parking parking) {
            this.parking = parking;
        }

        @Override
        public void run() {
            parking.park();
        }
    }

    public static void main(String[] args) {
        Parking parking = new Parking(3);
        for(int i = 0; i < 5; i++) {
            new Car(parking).start();
        }

        System.out.println("停车结束");
    }
}
