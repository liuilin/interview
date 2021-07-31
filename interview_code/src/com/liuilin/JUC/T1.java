package com.liuilin.JUC;

/**
 * @author liuqiang
 * @since 2021-07-30
 */
public class T1 implements Runnable {

    @Override
    public void run() {
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " " + instance);
    }
}
