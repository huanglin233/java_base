package com.hl.javase.enum_;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huanglin by 2021/5/15
 */
public class Pizza {
    private static final EnumSet<PizzaStatus> UNDELIVERED_PIZZA_STATUSES = EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);
    private PizzaStatus status;
    public enum PizzaStatus {
        /**
         * 序号
         */
        ORDERED(0) {
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        /**
         * 状态
         */
        READY(1) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        /**
         * 交付
         */
        DELIVERED(2) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };
        private final int value;

        private PizzaStatus(int value) {
            this.value = value;
        }

        public boolean isOrdered() {return false;}
        public boolean isReady() {return false;}
        public boolean isDelivered(){return false;}
        public int getValue() {
            return value;
        }
    }

    public void printValue() {
        System.out.println("time to delivery is" + this.getStatus().getValue());
    }

    public static List<Pizza> getAll(List<Pizza> input) {
        return input.stream().filter((s) -> UNDELIVERED_PIZZA_STATUSES.contains(s.getStatus())).collect(Collectors.toList());
    }

    public boolean isDeliverable() {
        return this.status.isDelivered();
    }

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public static void main(String[] args) {
        System.out.println(false);
        System.out.println(getDeliveryTimeInDays(PizzaStatus.DELIVERED));

        Pizza testPz = new Pizza();
        testPz.setStatus(PizzaStatus.DELIVERED);
        System.out.println(testPz.isDeliverable());

        List<Pizza> pzList = new ArrayList<>();
        Pizza pz1 = new Pizza();
        pz1.setStatus(Pizza.PizzaStatus.DELIVERED);
        Pizza pz2 = new Pizza();
        pz2.setStatus(Pizza.PizzaStatus.DELIVERED);
        Pizza pz3 = new Pizza();
        pz3.setStatus(Pizza.PizzaStatus.ORDERED);
        Pizza pz4 = new Pizza();
        pz4.setStatus(Pizza.PizzaStatus.READY);

        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);

        List<Pizza> undeliveredPzs = Pizza.getAll(pzList);
        System.out.println(undeliveredPzs.size() == 3);


        PizzaDeliveryStrategy deliveryStrategy = PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy();
    }

    public static int getDeliveryTimeInDays(PizzaStatus status) {
        switch (status) {
            case ORDERED:
                return 0;
            case READY:
                return 1;
            case DELIVERED:
                return 2;
                default:
                    return -1;
        }
    }
}