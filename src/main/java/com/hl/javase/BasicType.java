package com.hl.javase;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author huanglin by 2021/5/17
 */
public class BasicType {
    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println(i1 == i2); // true
        System.out.println(i3 == i4); // false

        Double d1 = 100.0;
        Double d2 = 100.0;
        Double d3 = 200.0;
        Double d4 = 200.0;

        System.out.println(d1 == d2); // false
        System.out.println(d3 == d4); // false

        Boolean b1 = false;
        Boolean b2 = false;
        Boolean b3 = true;
        Boolean b4 = true;

        System.out.println(b1 == b2); // true
        System.out.println(b3 == b4); // true

        int     A = 1;
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Integer integer = Integer.valueOf(1);
        Long g = 3L;
        Long h = 2L;

        System.out.println(A == integer); // true
        System.out.println(a == integer); // true
        System.out.println(c == d); // true
        System.out.println(e == f); // false
        System.out.println(c == (a+b)); // true
        System.out.println(c.equals(a+b)); // true
        System.out.println(g == (a+b)); // true
        System.out.println(g.equals(a+b)); // false
        System.out.println(g.equals(a+h)); // true
        
        BigInteger bigInteger = new BigInteger("2000");
        BigDecimal bigDeciml  = new BigDecimal("333.0");
        // 该方法用于返回此BigInteger的二进制补码表示形式中不同于其符号位的位数。
        System.out.println(bigInteger.bitCount()); // 6
        // 该方法用于返回此BigInteger的最小2补码表示形式中的位数，不包括符号位
        System.out.println(bigInteger.bitLength()); //11
        System.out.println(bigInteger.intValue()); // 2000
        System.out.println(bigInteger.doubleValue()); // 2000.0
        System.out.println(bigInteger.intValue() % bigDeciml.doubleValue()); // 2.0
        
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }
}
