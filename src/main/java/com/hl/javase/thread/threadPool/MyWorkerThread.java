package com.hl.javase.thread.threadPool;

/**
 * @author huanglin
 * @date 2024/01/02 23:40
 */
public class MyWorkerThread implements Runnable{

    private String command;

    public MyWorkerThread(String command){
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 开始; Command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName()+" 结束");
    }

    private void processCommand() {
        retry:
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
