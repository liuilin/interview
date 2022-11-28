package com.liuilin.java.lang;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuqiang
 * @since 2021-08-04
 */
public class String {

    static {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
        } finally {
            lock.unlock();
        }
        System.out.println("Hello string...");
    }

    public static void main(java.lang.String[] args) {
        if (true) {
            System.out.println("a");
            return;
        } else if (true) {
            System.out.println("b");
        }
        System.out.println("Hello string1...");
    }

}
