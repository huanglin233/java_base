package com.hl.jvm;

/**
 * @author huanglin
 * @date 2024/03/14 23:22
 */
public class JVM1 {

    public static void main(String[] args) {
        int i = 1;
        int j = 2;
        int k = i + j;
        String s = "hello";
        s.intern();

        System.out.println(k);
    }
}
