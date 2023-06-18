package com.hl.javase.agent.aop;

/**
 * @author huanglin
 * @date 2021/6/6 上午11:07
 */
public class Test {
    public static void main(String[] args) {
        IHello hello = (IHello) new DynaProxyHello().bind(new Hello(), new DLogger());
        hello.sayHello("你好");
    }
}