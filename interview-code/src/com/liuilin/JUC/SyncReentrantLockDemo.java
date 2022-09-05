package com.liuilin.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现 A->B->C 三个线程启动，要求如下：
 *  T1 打印 1 次，T2 打印 2 次，T3 打印 3 次
 *  紧接着
 *  T1 打印 1 次，T2 打印 2 次，T3 打印 3 次
 *  来 2 轮
 *
 * @author liuqiang
 * @since 2021-08-03
 */
public class SyncReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 2; i++) {
                shareResource.print1();
            }
        }, "T1").start();
        new Thread(() -> {
            for (int i = 1; i <= 2; i++) {
                shareResource.print2();
            }
        }, "T2").start();
        new Thread(() -> {
            for (int i = 1; i <= 2; i++) {
                shareResource.print3();
            }
        }, "T3").start();
    }
    // =========================== print out ===========================
    // T1 print1() 打印第 1 次
    // T2 print2() 打印第 1 次
    // T2 print2() 打印第 2 次
    // T3 print3() 打印第 1 次
    // T3 print3() 打印第 2 次
    // T3 print3() 打印第 3 次
    // T1 print1() 打印第 1 次
    // T2 print2() 打印第 1 次
    // T2 print2() 打印第 2 次
    // T3 print3() 打印第 1 次
    // T3 print3() 打印第 2 次
    // T3 print3() 打印第 3 次
}

class ShareResource {

    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print1() {
        lock.lock();
        try {
            // 判断
            while (num != 1) {
                c1.await();
            }
            // 干活
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + " print1() 打印第 " + i + " 次");
            }
            // 唤醒通知 2 号线程
            num = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print2() {
        lock.lock();
        try {
            // 判断
            while (num != 2) {
                c2.await();
            }
            // 干活
            for (int i = 1; i <= 2; i++) {
                System.out.println(Thread.currentThread().getName() + " print2() 打印第 " + i + " 次");
            }
            // 唤醒通知 3 号线程
            num = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print3() {
        lock.lock();
        try {
            // 判断
            while (num != 3) {
                c3.await();
            }
            // 干活
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " print3() 打印第 " + i + " 次");
            }
            // 重新唤醒通知 1 号线程
            num = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
