package com.liuilin.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA 问题解决
 *
 * @author liuqiang
 * @since 2021-08-02
 */
public class ABA {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(10);
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10, 1);

    public static void main(String[] args) {
        System.out.println("============================= ABA 问题产生 =============================");
        new Thread(() -> {
            atomicReference.compareAndSet(10, 11);
            atomicReference.compareAndSet(11, 10);
        }, "T1").start();

        new Thread(() -> {
            // 确保上面的 T1 线程先完成 ABA 问题
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicReference.compareAndSet(10, 100) + " --- " + atomicReference.get());
        }, "T2").start();

        // 确保上面线程已执行完毕
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("============================= ABA 问题解决 =============================");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " --- " + "第 1 次版本号：" + stamp);
            //暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicStampedReference.compareAndSet(10, 11, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " --- " + "第 2 次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(11, 10, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        }, "T3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " --- " + "第 1 次版本号：" + stamp);
            // 确保上面的 T3 线程先完成 ABA 问题
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean res = atomicStampedReference.compareAndSet(10, 100, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + " --- " + res + "，当前版本号：" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + " --- " + "最新值" + atomicStampedReference.getReference());
        }, "T4").start();
    }
    // ============================= ABA 问题产生 =============================
    // true --- 100
    // ============================= ABA 问题解决 =============================
    // T3 --- 第 1 次版本号：1
    // T4 --- 第 1 次版本号：1
    // T3 --- 第 2 次版本号：2
    // T4 --- false，当前版本号：3
    // T4 --- 最新值10
}
