package com.liuilin.JUC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liuqiang
 * @since 2021-08-19
 */
public class ListTest {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            objects.add(i);
        }
        System.out.println("objects = " + objects);
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();

        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            hashMap.put(i + "", i + "");
        }
    }
}
