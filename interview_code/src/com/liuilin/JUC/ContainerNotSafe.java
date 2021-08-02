package com.liuilin.JUC;

import java.util.*;

/**
 * @author liuqiang
 * @since 2021-08-02
 */
public class ContainerNotSafe {

    public static void main(String[] args) {
        // java.util.ConcurrentModificationException
        List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
