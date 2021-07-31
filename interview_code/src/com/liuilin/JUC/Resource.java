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
        for (int i = 1; i <= 2; i++) {
            new Thread(() -> {
                resource.method1();
                resource.method2();
            }, String.valueOf(i)).start();
        }
    }

    public void method1() {
        a = 1;              // 语句 1
        flag = true;        // 语句 2
    }

    // 多线程环境中线程交替执行，由于编译器优化重排的存在，两个线程使用的变量能否保持一致性是无法确定的，以至于结果无法预测
    public void method2() {
        if (flag) {
            a = a + 5;      // 语句 3
            System.out.println(Thread.currentThread().getName() + "--- return value --- " + a);
        }
    }
}
