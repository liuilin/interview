package com.liuilin.JVM.Ref;

import java.lang.ref.SoftReference;

/**
 * 软引用
 *
 * @author liuqiang
 * @since 2021-08-09
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
//        softRefMemoryEnough();
        softRefMemoryNotEnough();
    }

    /**
     * VM 配置，故意产生大对象并配置小的内存，让它内存不够用了导致 OOM，看软引用的回收情况
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    private static void softRefMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println("o1 = " + o1);
        System.out.println("softReference = " + softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024]; // new 30m 大小的对象，导致 OOM
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("o1 = " + o1);
            System.out.println("softReference = " + softReference.get());
        }
    }
    // o1 = java.lang.Object@75b84c92
    // softReference = java.lang.Object@75b84c92
    // o1 = null
    // softReference = null

    private static void softRefMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println("o1 = " + o1);
        System.out.println("softReference = " + softReference.get());

        o1 = null;
        System.gc();
        System.out.println("o1 = " + o1);
        System.out.println("softReference = " + softReference.get());
    }
    // ================================== print out ==================================
    // o1 = java.lang.Object@75b84c92
    // softReference = softReference = java.lang.Object@75b84c92
    // o1 = null
    // softReference = java.lang.Object@75b84c92
}
