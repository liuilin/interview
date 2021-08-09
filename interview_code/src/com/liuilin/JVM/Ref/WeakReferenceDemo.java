package com.liuilin.JVM.Ref;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * @author liuqiang
 * @since 2021-08-09
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println("o1 = " + o1);
        System.out.println("weakReference = " + weakReference.get());

        o1 = null;
        System.gc();

        System.out.println("o1 = " + o1);
        // 此时只将 o1 置为了 null，然后进行 GC，没有动 WeakReference 对象，但结果是只要进行了 GC，软引用对象就会被回收
        System.out.println("weakReference = " + weakReference.get());
    }
    // ================================== print out ==================================
    // o1 = java.lang.Object@75b84c92
    // weakReference = java.lang.Object@75b84c92
    // o1 = null
    // weakReference = null
}
