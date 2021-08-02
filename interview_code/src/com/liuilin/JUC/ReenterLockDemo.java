package com.liuilin.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁代码演示
 * @author liuqiang
 * @since 2021-08-02
 */
public class ReenterLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        System.out.println("=========================== Synchronized ===========================");
        new Thread(() -> {
            phone.sendSMS();
        }, "T1").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "T2").start();

        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("=========================== ReentrantLock ===========================");
        Thread t3 = new Thread(phone, "T3");
        Thread t4 = new Thread(phone, "T4");
        t3.start();
        t4.start();
    }
    // =========================== Synchronized ===========================
    // T1 --- send message...
    // T1 --- send email
    // T2 --- send message...
    // T2 --- send email
    // =========================== ReentrantLock ===========================
    // T3 --- invoke get()...
    // T3 --- invoke set()
    // T4 --- invoke get()...
    // T4 --- invoke set()

}

class Phone implements Runnable {

    Lock lock = new ReentrantLock();

    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + " --- send message...");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + " --- send email");
    }

    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " --- invoke get()...");
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        System.out.println(Thread.currentThread().getName() + " --- invoke set()");
    }
}