package com.liuilin.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 收集七龙珠
 *
 * @author liuqiang
 * @since 2021-08-02
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println(Thread.currentThread().getName() + " --- 七颗龙珠收集完成，开始召唤神龙...");
        });
        for (int i = 1; i <= 7; i++) {
            int finalInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 收集到了第" + finalInt + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}