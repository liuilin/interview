package com.liuilin.JVM.Ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 引用队列
 *
 * @author liuqiang
 * @since 2021-08-09
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);

        System.out.println("o1 = " + o1);
        System.out.println("weakReference = " + weakReference.get());
        System.out.println("referenceQueue = " + referenceQueue.poll());

        o1 = null;
        System.gc();
        Thread.sleep(500); // 确保执行完 GC
        System.out.println("==================== ok ====================");

        System.out.println("o1 = " + o1);
        System.out.println("weakReference = " + weakReference.get());
        // 打印出来对象，原因是弱引用被垃圾回收前一刻会放入引用队列
        System.out.println("referenceQueue = " + referenceQueue.poll());
    }
    // ================================ print out ================================
    // o1 = java.lang.Object@75b84c92
    // weakReference = java.lang.Object@75b84c92
    // referenceQueue = null
    // ==================== ok ====================
    // o1 = null
    // weakReference = null
    // referenceQueue = java.lang.ref.WeakReference@6bc7c054
}
