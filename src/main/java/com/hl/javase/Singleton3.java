package com.hl.javase;

/**
 * 饿汉式，线程安全
 *
 * @author huanglin by 2021/5/17
 */
public class Singleton3 {

    private static Singleton3 instance = new Singleton3();

    private Singleton3() {}

    public static Singleton3 getInstance() {
        return instance;
    }
}