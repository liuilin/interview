package com.liuilin.JVM.ClassLoader;

/**
 * @author liuqiang
 * @since 2021-08-05
 */
public class DynamicLinkingTest {

    private static int num = 10;

    public void methodA() {
        System.out.println("DynamicLinkingTest.methodA");
    }

    public void methodB() {
        System.out.println("DynamicLinkingTest.methodB");
        methodA();
        num++;
    }

}
































































































































