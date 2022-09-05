package com.liuilin.JVM.ClassLoader;

/**
 * 类加载器演示
 *
 * @author liuqiang
 * @since 2021-08-04
 */
public class MyObject {

    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(obj.getClass().getClassLoader());

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
    }
    // =========================== print out ===========================
    // null
    // sun.misc.Launcher$AppClassLoader@18b4aac2
    // sun.misc.Launcher$ExtClassLoader@74a14482
    // null

}
