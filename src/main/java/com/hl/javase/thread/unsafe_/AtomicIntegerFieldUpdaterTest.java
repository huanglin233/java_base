package com.hl.javase.thread.unsafe_;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author huanglin
 * @date 2023/07/18 22:55
 */
public class AtomicIntegerFieldUpdaterTest {


    public AtomicIntegerFieldUpdater<AtomicData> updater(String name) {

        /**
         * AtomicIntegerFieldUpdater: 原子更新整型的字段的更新器。
         * AtomicLongFieldUpdater: 原子更新长整型字段的更新器。
         * AtomicReferenceFieldUpdater:原子更新包装类型字段的更新器。
         */
        return AtomicIntegerFieldUpdater.newUpdater(AtomicData.class, name);
    }

    public void doIt() {
        AtomicData data = new AtomicData();
        System.out.println("publicVar = " + updater("publicVar").getAndAdd(data, 2));
        System.out.println("protectedVar = " + updater("protectedVar").getAndAdd(data, 2));
        // Class com.hl.javase.thread.unsafe_.AtomicIntegerFieldUpdaterTest can not access a member of class com.hl.javase.thread.unsafe_.AtomicData with modifiers "private volatile"
        // System.out.println("privateVar = " + updater("privateVar").getAndAdd(data, 2));

        // Exception in thread "main" java.lang.IllegalArgumentException
        // System.out.println("staticVar = " + updater("staticVar").getAndIncrement(data));

        // Exception in thread "main" java.lang.IllegalArgumentException: Must be volatile type
        // System.out.println("finalVar = " + updater("finalVar").getAndAdd(data, 2));

        // Exception in thread "main" java.lang.IllegalArgumentException: Must be integer type
        // System.out.println("integerVar = " + updater("integerVar").getAndIncrement(data));
        // System.out.println("longVar = " + updater("longVar").getAndIncrement(data));

        // java.lang.NoSuchFieldException: notVar
        // System.out.println("notVar = " + updater("notVar").getAndIncrement(data));
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterTest test = new AtomicIntegerFieldUpdaterTest();
        test.doIt();
    }
}
