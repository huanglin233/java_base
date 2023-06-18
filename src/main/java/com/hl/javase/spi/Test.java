package com.hl.javase.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author huanglin
 * @date 2023/05/24 22:05
 */
public class Test {

    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            search.searchDoc("hello world");
        }
    }
}
