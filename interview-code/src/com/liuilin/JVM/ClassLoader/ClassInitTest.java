package com.liuilin.JVM.ClassLoader;

/**
 * @author liuqiang
 * @since 2021-08-04
 */
public class ClassInitTest {

    private static int num = 1;

    static {
        num = 2;
        number = 20;
        // System.out.println(num);
        // System.out.println(number); // 报错
    }

    // 链接阶段下的 prepare 阶段，先把 number = 0 --> inital: 20 --> 10
    private static int number = 10;

    public static void main(String[] args) {
        System.out.println(ClassInitTest.num);
        System.out.println(ClassInitTest.number);
    }
}




























