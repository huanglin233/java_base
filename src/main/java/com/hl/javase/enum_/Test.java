package com.hl.javase.enum_;

/**
 * @author huanglin by 2021/5/15
 */
public class Test {

    public static void main(String[] args) {
        // ORDERED
        System.out.println(PizzaStatus.ORDERED.name());
        // ORDERED
        System.out.println(PizzaStatus.ORDERED);
        // class java.lang.String
        System.out.println(PizzaStatus.ORDERED.name().getClass());
        // class com.hl.javase.enumStudy.PizzaStatus
        System.out.println(PizzaStatus.ORDERED.getClass());
    }
}