package com.liuilin.JUC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS: compare and swap
 *
 * @author liuqiang
 * @since 2021-07-31
 */
public class CAS {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(10, 9) + " current value: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(10, 8) + " current value: " + atomicInteger.get());
    }
}
