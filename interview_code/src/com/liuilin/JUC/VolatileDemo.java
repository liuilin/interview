package com.liuilin.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. 验证 volatile 可见性
 *  1.1 假如 int number = 0; number 变量之前根本没有添加 volatile 关键字修饰，没有可见性
 *  1.2 加入 volatile 后可以解决可见性问题
 *
 * 2. 验证 volatile 不保证原子性
 *  2.1 原子性指的是什么意思？
 *  不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或分割。需要整体完整。要么同时成功，要么同时失败。
 *  2.2 volatile 不保证原子性案例
 *  2.3 why
 *  2.4 如何解决原子性
 *      - 加 Synchronized
 *      - 使用 JUC 下面的 AtomicInteger
 *
 * @author liuqiang
 * @since 2021-07-29
 */
public class VolatileDemo {

    public static void main(String[] args) {
        Goods goods = new Goods();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    goods.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待前面的所有线程都计算完成后再走后面的 main 线程（为什么是大于 2？因为后台默认有两个线程：1. main 线程；2 GC 线程）
        while (Thread.activeCount() > 2) {
            Thread.yield(); // 挂起不执行此线程
        }

        System.out.println(Thread.currentThread().getName() + "final num value --- " + goods.num);
    }

    // volatile 可以保证可见性，及时通知其它线程，主物理内存的值已经被修改。
    private static void visualByVolatile() {
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
    AtomicInteger atomicInteger = new AtomicInteger();

    public void buyGoods() {
        this.num = this.num - 1;
    }

    // 注意此时 number 前面是加了 volatile 关键字修饰的，所以说 volatile 不保证原子性
    public void addPlusPlus() {
        num++;
    }

    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }

}