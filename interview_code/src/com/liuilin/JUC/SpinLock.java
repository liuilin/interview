package com.liuilin.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author liuqiang
 * @since 2021-08-02
 */
public class SpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        new Thread(() -> {
            spinLock.lock();
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLock.unlock();
        }, "T1").start();

        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(() -> {
            spinLock.lock();
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLock.unlock();
        }, "T2").start();
    }
    // =========================== print out ===========================
    // T1 coming...
    // T2 coming...
    // T1 unlock...
    // T2 unlock...

    private void lock() {
        System.out.println(Thread.currentThread().getName() + " coming...");
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
        }
    }

    private void unlock() {
        System.out.println(Thread.currentThread().getName() + " unlock...");
        atomicReference.compareAndSet(Thread.currentThread(), null);
    }
}
