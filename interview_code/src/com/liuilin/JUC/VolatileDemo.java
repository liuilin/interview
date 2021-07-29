package com.liuilin.JUC;

import java.util.concurrent.TimeUnit;

/**
 * volatile 可见性验证
 *
 * @author liuqiang
 * @since 2021-07-29
 */
public class VolatileDemo {

    public static void main(String[] args) {
        Goods goods = new Goods();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " --- come in");
            // 执行业务逻辑
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            goods.buyGoods();
            System.out.println(Thread.currentThread().getName() + " --- update");
        }, "T1").start();

        // main 线程
        while (goods.num == 10) {
            // main 现在一直会在这里循环等待，直到商品库存不等于 10
        }
        System.out.println(Thread.currentThread().getName() + " --- mission complete.");
    }

}

class Goods {

    // int num = 10;
    volatile int num = 10; // 用 volatile 保证数据可见性，没加 volatile 会导致 main 线程一直等待不结束

    public void buyGoods() {
        this.num = this.num - 1;
    }
}