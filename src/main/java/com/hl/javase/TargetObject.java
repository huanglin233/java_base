package com.hl.javase;

/**
 * @author huanglin by 2021/5/17
 */
public class TargetObject {
    private String value;

    public TargetObject(){
        value = "hello";
    }

    public void publicMethod(String s) {
        System.out.println("it is " + s);
    }

    private void privateMethod(String s){
        System.out.println("it is " + s);
    }
}