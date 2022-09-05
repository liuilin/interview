package com.liuilin.JVM.ClassLoader;

/**
 * @author liuqiang
 * @since 2021-08-04
 */
public class DeadThreadTest {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName() + " 结束");
        }, "T1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName() + " 结束");
        }, "T2").start();
    }

}

class DeadThread {

    static {
        if (true) {
            System.out.println(Thread.currentThread().getName() + " 正在初始化当前类...");
            while (true) { // while 执行，一直循环相当于加锁
            }
        }
    }
}