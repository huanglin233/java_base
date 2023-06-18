package com.hl.javase;

/**
 * 双重锁验证，单列
 *
 * @author huanglin by 2021/5/17
 */
public class Singleton4 {

    private volatile static Singleton4 instance;

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if(instance == null) {
            synchronized (Singleton4.class) {
                if(instance == null) {
                    instance = new Singleton4();
                }
            }
        }

        return instance;
    }
}