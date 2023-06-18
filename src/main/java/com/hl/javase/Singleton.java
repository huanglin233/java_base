package com.hl.javase;

/**
 * 懒汉式，线程不安全
 *
 * @author huanglin by 2021/5/17
 */
public class Singleton {
    /**
     * 声明为 private 避免调用默认构造方法创建对象
     */
    private  Singleton(){}

    /**
     * 声明为 private 表明静态内部该类只能在该 Singleton 类中被访问
     */
    private static class SingletonHolder{
        private static final Singleton SINGLETON = new Singleton();
    }

    public static Singleton getUniqueInstance() {
        return SingletonHolder.SINGLETON;
    }
}