package com.hl.javase.thread.unsafe_;

/**
 * @author huanglin
 * @date 2023/07/18 23:06
 */
public class AtomicData {

    public    volatile int     publicVar    = 3;
    protected volatile int     protectedVar = 4;
    private   volatile int     privateVar   = 5;
    public    volatile Integer integerVar   = 6;
    public    volatile Long    longVar      = 7L;

    public volatile static int staticVar = 8;
    public final int finalVar = 9;
}
