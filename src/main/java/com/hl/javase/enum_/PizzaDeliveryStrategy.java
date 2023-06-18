package com.hl.javase.enum_;

/**
 * @author huanglin by 2021/5/15
 */

public enum PizzaDeliveryStrategy {
    /**
     * EXPRESS
     */
    EXPRESS {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("Pizza will be delivered in express mode");
        }
    },
    /**
     * NORMAL
     */
    NORMAL {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("Pizza will be delivered in normal mode");
        }
    };

    public abstract void deliver(Pizza pz);
}