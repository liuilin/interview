package com.liuilin.JVM.OOM;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * 配置参数
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * <p>
 * 故障现象
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 * <p>
 * 导致原因
 * 导致原因：写 NIO 程序经常使用 ByteBuffer 来读取或者写入数据，这是一种基于通道（Channe）与缓冲区（Buffer）的 I/O 方式，它可以使用 Native 函数库直接分配堆外内存，然后通过一个存储在 Java 堆里的 DirectByteBuffer 对象作为这块内存的引用进行操作。这样就能在一些场景中显著提高性能，因为避免了在 Java 堆 和 Native 堆中来回复制数据
 * ByteBuffer.allocate (capability) 第一种方式是分配 JVM 堆内存，属于 GC 管辖范围，由于需要拷贝所以速度相对较慢
 * ByteBuffer. allocteDirect (capability) 第二种方式是分配 OS 本地内存，不属于 GC 管辖范围，由于不需内存拷贝所以速度相对较快。
 * 但如果不断分配本地内存，堆内存很少使用，那么 VM 就不需要执行 GC, DirectByteBuffer 对象们就不会被回收，这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出现 OutOfMemoryError，那程序就直接崩溃了。
 *
 * @author liuqiang
 * @since 2021-08-10
 */
public class DirectBufferMemory {
    public static void main(String[] args) {
        System.out.println("配置的 MaxDirectMemory：" + sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024 + "MB");
        //暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
