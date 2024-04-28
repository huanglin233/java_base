package com.hl.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglin
 * @date 2024/04/24 22:52
 */
public class OutOfMemoryErrorTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while(true) {
            list.add("1");
        }
    }
}
