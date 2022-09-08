package com.liuilin.JVM.GC;

/**
 * 可作为 GCRoots 的对象：
 * i. 虚拟机栈中引用的对象（也就是栈帧中的局部变量表）
 * ii. 方法区中的类静态属性引用
 * iii. 方法区中常量引用的对象
 * iv. 本地方法栈中 JNI（java native interface）引用的对象
 *
 * @author liuqiang
 * @since 2021-08-09
 */
public class GCRootsDemo {

//    private static final GCRootsDemo3 g3 = new GCRootsDemo3();
//    private static GCRootsDemo2 g2;

    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        GCRootsDemo g1 = new GCRootsDemo();
    }
}
