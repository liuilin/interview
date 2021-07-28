package com.liumulin;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author liuqiang
 * @since 2021-07-28
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {

    // 缓存坑位
    private Integer capacity;

    /**
     * the ordering mode -
     * <tt>true</tt> for access-order
     * <tt>false</tt> for insertion-order
     *
     * @param capacity 坑位
     */
    public LruCache(int capacity) {
        super(capacity, 0.75F, false);
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(3);
        lruCache.put(1, 11);
        lruCache.put(2, 22);
        lruCache.put(3, 33);
        System.out.println(lruCache.keySet());
        lruCache.put(4, 44);
        System.out.println(lruCache.keySet());
        lruCache.put(3, 33);
        System.out.println(lruCache.keySet());
        lruCache.put(3, 33);
        System.out.println(lruCache.keySet());
        lruCache.put(5, 55);
        System.out.println(lruCache.keySet());
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return super.size() > capacity;
    }
}
/** access-order = true
 * [1, 2, 3]
 * [2, 3, 4]
 * [2, 4, 3]
 * [2, 4, 3]
 * [4, 3, 5]
 */

/** access-order = false
 * [1, 2, 3]
 * [2, 3, 4]
 * [2, 3, 4]
 * [2, 3, 4]
 * [3, 4, 5]
 */