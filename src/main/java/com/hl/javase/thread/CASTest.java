package com.hl.javase.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author huanglin
 * @date 2021/7/2 下午11:54
 */
public class CASTest {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.compareAndSet(100, 110);
                atomicInteger.compareAndSet(110, 100);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    // t1,执行完
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("AtomicInteger: " + atomicInteger.compareAndSet(100, 120));
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 让 t4先获取stamp，导致预期时间戳不一致
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            // 预期引用100, 更新后的引用110,预期标识getStamp() 更新后的标志getStamp() + 1
                atomicStampedReference.compareAndSet(100, 110, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                atomicStampedReference.compareAndSet(110, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("AtomicStampedReference: " + atomicStampedReference.compareAndSet(100, 120, stamp, stamp + 1));
            }
        });

        t3.start();
        t4.start();
    }
}
