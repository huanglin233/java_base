package com.hl.javase.thread.lockSupport_;

/**
 * @author huanglin
 * @date 2023/07/19 23:48
 */
public class Thread3 extends Thread{

    private Object object;

    public Thread3(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.println("before interrupt");
        try {
            // 休眠3s
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread thread = (Thread) object;
        // 中断线程
        thread.interrupt();
        System.out.println("after interrupt");
    }
}
