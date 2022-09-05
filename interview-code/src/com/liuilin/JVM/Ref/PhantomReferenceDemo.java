package com.liuilin.JVM.Ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.PhantomReference;

/**
 * 虚引用
 *
 * @author liuqiang
 * @since 2021-08-09
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println("o1 = " + o1);
        System.out.println("phantomReference = " + phantomReference.get());
        System.out.println("referenceQueue = " + referenceQueue.poll());

        o1 = null;
        System.gc();
        Thread.sleep(500); // 确保执行完 GC
        System.out.println("==================== ok ====================");

        System.out.println("o1 = " + o1);
        System.out.println("phantomReference = " + phantomReference.get());
        // 打印出来对象，原因是弱引用被垃圾回收前一刻会放入引用队列
        System.out.println("referenceQueue = " + referenceQueue.poll());
    }
    // ================================ print out ================================
    // o1 = java.lang.Object@75b84c92
    // phantomReference = null
    // referenceQueue = null
    // ==================== ok ====================
    // o1 = null
    // phantomReference = null
    // referenceQueue = java.lang.ref.PhantomReference@6bc7c054
}
