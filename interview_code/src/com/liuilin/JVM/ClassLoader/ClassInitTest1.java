package com.liuilin.JVM.ClassLoader;

/**
 * @author liuqiang
 * @since 2021-08-04
 */
public class ClassInitTest1 {

    public static void main(String[] args) {
        System.out.println(Son.b);
    }

    static class Father {

        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Son extends Father {

        public static int b = A;
    }

}
