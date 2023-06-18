package com.hl.javase.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huanglin by 2021/5/15
 */
public class Test {
    public static void main(String[] args) {
        TreeMap<MyCompareTo, String> data = new TreeMap<MyCompareTo, String>();
        data.put(new MyCompareTo("aa", 12), "aa12");
        data.put(new MyCompareTo("bb",11), "bb11");
        data.put(new MyCompareTo("cc", 13), "cc13");
        data.put(new MyCompareTo("dd", 14), "dd14");

        // 得到key的值的同时得到key所对应的值
        Set<MyCompareTo> myCompareTos = data.keySet();
        for(MyCompareTo myCompareTo : myCompareTos) {
            System.out.println(myCompareTo.getName() + " - " + myCompareTo.getAge());
        }
        Map<Object, Object> myMap = new HashMap<>(1444, 2);
        myMap.put(1,1);
        System.out.println(myMap);
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>(18, 0.75f, 18);
        System.out.println(concurrentHashMap);
    }
}