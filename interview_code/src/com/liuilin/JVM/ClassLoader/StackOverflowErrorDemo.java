package com.liuilin.JVM.ClassLoader;

/**
 * java.lang.StackOverflowError
 * 默认情况下 i = 9879
 * 设置栈大小：-Xss256k，i = 2304
 *
 * @author liuqiang
 * @since 2021-08-04
 */
public class StackOverflowErrorDemo {

    // java.lang.StackOverflowError
    // 默认情况下 i = 9879
    // 设置栈大小：-Xss256k，i = 2304
    private static int i = 1;
    public static void main(String[] args) {
        System.out.println(i++);
        main(args);
    }
//    public static void main(String[] args) {
//        int i = 10;
//        int j = 20;
//        int k = i + j;
//    }
    // =========================== javap -c print ===========================

}
