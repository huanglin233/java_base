package com.hl.javase.thread.stampedLock;

import java.util.concurrent.locks.StampedLock;

/**
 * stampedlock操作实体类
 * @author huanglin
 * @date 2023/07/11 23:36
 */
public class Point {

     private double x, y;
     private final StampedLock SL = new StampedLock();

    /***
     * 移动点位
     * @param deltaX
     * @param deltaY
     */
     void move(double deltaX, double deltaY) {
         long stamp = SL.writeLock(); // 使用写锁-独占操作
         try {
             x += deltaX;
             y += deltaY;
         } finally {
             SL.unlockWrite(stamp);

         }
     }

     double distanceFromOrigin() {
         long stamp = SL.tryOptimisticRead(); // 获取乐观读锁
         double currentX = x, currentY = y; // copy x,y变量到内存中
         if(!SL.validate(stamp)) { // 校验锁状态-判断数据是否一致
             System.out.println("数据发生改变");
             stamp = SL.readLock(); // 获取悲观读锁
             try {
                 // copy x,y变量到内存中
                 currentX = x;
                 currentY = y;
             } finally {
                 SL.unlockRead(stamp);
             }
         }

         return Math.sqrt(currentX * currentX + currentY * currentY);
     }

    public static void main(String[] args) throws InterruptedException {
        Point point = new Point();
        new Thread("move"){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for(int i = 0; i < 1000; i++) {
                    point.move(3.00, 3.00);
                }
            }
        }.start();

        new Thread("move"){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for(int i = 0; i < 100; i++) {
                    point.move(3.00, 3.00);
                }
            }
        }.start();

        new Thread("move"){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for(int i = 0; i < 100; i++) {
                    point.move(3.00, 3.00);
                }
            }
        }.start();


        Thread.sleep(1000L);
        for(int i = 0; i < 1000; i++) {
            double v = point.distanceFromOrigin();
            System.out.println("当前距离远点: " + v);
        }
    }
}
