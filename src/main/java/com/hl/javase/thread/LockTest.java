package com.hl.javase.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huanglin by 2021/5/16
 */
public class LockTest extends Thread{
    private final Lock lock = new ReentrantLock();
    // Lock lock=new ReentrantLock(true);//公平锁
    // Lock lock=new ReentrantLock(false);//非公平锁
    /**
     * 线程条件Condition
     */
    private final Condition condition = lock.newCondition();

    public void testMethod() {
        try {
            lock.lock(); // lock加锁
            // 1.wait方法等待
            System.out.println("开始wait");
            condition.await();
            // 通过创建 Condition 对象来使线程 wait，必须先执行 lock.lock 方法获得锁
            // signal方法唤醒
            // condition.signal(); //通过创建 Condition 对象来使线程 wait，必须先执行 lock.lock 方法获得锁
            int loop = 5;
             for (int i = 0; i < loop; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()+ (" " + (i + 1)));
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        testMethod();
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        lockTest.start();
        System.out.println("1000 * 10ms后唤醒线程");
        Thread.sleep(1000 * 10);
        System.out.println("唤醒线程");
        lockTest.lock.lock();
        lockTest.condition.signal();
        lockTest.lock.unlock();
    }
}