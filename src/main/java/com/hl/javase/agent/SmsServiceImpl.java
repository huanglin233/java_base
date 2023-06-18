package com.hl.javase.agent;

/**
 * @author huanglin by 2021/5/15
 */
public class SmsServiceImpl implements SmsService{
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}