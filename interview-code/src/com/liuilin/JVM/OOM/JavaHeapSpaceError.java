package com.liuilin.JVM.OOM;

/**
 * @author liuqiang
 * @since 2021-08-10
 */
public class JavaHeapSpaceError {
    public static void main(String[] args) {
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        // -Xms10m -Xmx10m ，JVM 最初始内存和最大内存都设置为 10m，new 一个 11m 的对象造成 OOM
        byte[] bytes = new byte[11 * 1024 * 1024];
    }
}
