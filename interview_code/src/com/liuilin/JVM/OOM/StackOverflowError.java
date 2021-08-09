package com.liuilin.JVM.OOM;

/**
 * @author liuqiang
 * @since 2021-08-10
 */
public class StackOverflowError {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError(); // Exception in thread "main" java.lang.StackOverflowError
    }
}
