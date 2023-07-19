package com.hl.javase.thread.unsafe_;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author huanglin
 * @date 2023/07/18 23:37
 */
public class AtomicTester {

    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 0);

    private static Thread t1() {
        return new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() + ",初始值 a = " + atomicStampedReference.getReference());
            int stamp = atomicStampedReference.getStamp(); // 获取当前标识识别
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("操作线程" + Thread.currentThread() + ", CAS操作结果：" + isCASSuccess);
        }, "主线程");
    }

    private static Thread t2() {
        return new Thread("干扰线程"){
            @Override
            public void run() {
               // 确保t1线程优先执行
                Thread.yield();
                atomicStampedReference.compareAndSet(1, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println("操作线程" + Thread.currentThread() + "【increment】,值 = " + atomicStampedReference.getReference());
                atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println("操作线程" + Thread.currentThread() + "【increment】,值 = " + atomicStampedReference.getReference());
            }
        };
    }

    public static void main(String[] args) {
        t1().start();
        t2().start();
    }
}
