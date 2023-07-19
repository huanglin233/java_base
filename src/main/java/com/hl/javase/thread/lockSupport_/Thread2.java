package com.hl.javase.thread.lockSupport_;

import java.util.concurrent.locks.LockSupport;

/**
 * @author huanglin
 * @date 2023/07/19 23:29
 */
public class Thread2 extends Thread{

    private Object object;

    public Thread2(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.println("before unpark");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取blocker
        System.out.println("blocker info " + LockSupport.getBlocker((Thread) object));
        // 释放许可
        LockSupport.unpark((Thread)object);
        // 休眠500ms,保证先执行park中得setBlocker(t, null)
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 再次获取blocker
        System.out.println("blocker info " + LockSupport.getBlocker((Thread) object));

        System.out.println("after unpark");
    }
}
