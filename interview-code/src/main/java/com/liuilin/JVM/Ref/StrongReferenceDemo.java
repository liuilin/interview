package com.liuilin.JVM.Ref;

/**
 * 强引用
 *
 * @author liuqiang
 * @since 2021-08-09
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object(); // 这样定义是强引用
        Object obj2 = obj1; // obj2 引用赋值
        obj1 = null; // obj1 置空
        System.gc(); // 垃圾收集
        System.out.println(obj2); //obj2 不受影响
    }
}
