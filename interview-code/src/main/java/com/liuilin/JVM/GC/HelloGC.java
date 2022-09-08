package com.liuilin.JVM.GC;

/**
 * @author liuqiang
 * @since 2021-08-09
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello Java");
        byte[] bytes = new byte[50 * 1024 * 1024];
//        Thread.sleep(Integer.MAX_VALUE);
    }
/*
    public static void main(String[] args) throws InterruptedException {
        long totalMemory = Runtime.getRuntime().totalMemory(); // JVM 总内存量
        long maxMemory = Runtime.getRuntime().maxMemory(); // JVM 试图使用的最大内存量
        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "（字节）" + (totalMemory / 1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "（字节）" + (maxMemory / 1024 / 1024) + "MB");
    }
*/
    // ==================================== print out(32G computer) ====================================
    // TOTAL_MEMORY(-Xms) = 508559360（字节）485MB
    // MAX_MEMORY(-Xmx) = 7540834304（字节）7191MB
}
