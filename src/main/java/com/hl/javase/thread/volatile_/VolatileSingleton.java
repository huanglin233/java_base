package com.hl.javase.thread.volatile_;

/**
 * @author huanglin
 * @date 2023/06/24 17:00
 */
public class VolatileSingleton {

    private volatile static VolatileSingleton instance;

    private VolatileSingleton(){}

    public static VolatileSingleton getInstance() {
        if(instance == null) {
            synchronized(VolatileSingleton.class) {
                if(instance == null) {
                    instance = new VolatileSingleton();
                }
            }
        }



        return instance;
    }
}
