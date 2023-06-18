package com.hl.javase.agent;

import com.hl.javase.agent.cglib.CglibProxyFactory;
import com.hl.javase.agent.jdkProxy.JdkProxyFactory;

/**
 * @author huanglin by 2021/5/15
 */
public class Test {
    public static void main(String[] args) {
        SmsService   smsService = new SmsServiceImpl();
        SmsProxyImpl smsProxy   = new SmsProxyImpl(smsService);
        smsProxy.send("xx");

        SmsService proxy = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        proxy.send("xxx");

        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("rexx");
    }
}