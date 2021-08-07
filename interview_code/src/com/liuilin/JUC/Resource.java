package com.liuilin.JUC;

/**
 * @author liuqiang
 * @since 2021-07-29
 */
public class Resource {

    int a = 0;
    boolean flag = false;

    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            resource.method1();
        }, "T1").start();

        new Thread(() -> {
            resource.method2();
        }, "T2").start();
    }

    public void method1() {
        flag = true;        // 语句 2
        a = 1;              // 语句 1
    }

    // 多线程环境中线程交替执行，由于编译器优化重排的存在，两个线程使用的变量（flag 和 a）无法保持一致，导致结果也不同
    public void method2() {
        if (flag) {
            a = a + 5;      // 语句 3
            System.out.println(Thread.currentThread().getName() + " --- return value --- " + a);
        }
    }
}
