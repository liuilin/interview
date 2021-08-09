package com.liuilin.JVM.Ref;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 弱引用 HashMap
 *
 * @author liuqiang
 * @since 2021-08-09
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
//        hashMap();
        weakHashMap();
    }

    private static void weakHashMap() {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(2); // 必须要是 new Integer(2) 对象，因为下面需要把 key 置为 null
        String value = "WeakHashMap";

        weakHashMap.put(key, value);
        System.out.println("weakHashMap = " + weakHashMap);

        key = null; // 只要 key 置为 null 并触发 GC，弱引用对象就会被回收
        System.out.println("weakHashMap = " + weakHashMap);

        System.gc();
        System.out.println("weakHashMap = " + weakHashMap);
    }
    // weakHashMap = {2=WeakHashMap}
    // weakHashMap = {2=WeakHashMap}
    // weakHashMap = {}

    private static void hashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        Integer key = 1;
        String value = "Nice";

        hashMap.put(key, value);
        System.out.println("hashMap = " + hashMap);

        key = null;
        System.out.println("hashMap = " + hashMap);

        System.gc();
        System.out.println("hashMap = " + hashMap);
    }
    // ================================ print out ================================
    // hashMap = {1=Nice}
    // hashMap = {1=Nice}
    // hashMap = {1=Nice}

}
