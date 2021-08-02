package com.liuilin.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author liuqiang
 * @since 2021-08-02
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 1; i <= 3; i++) {
            int finalInt = i;
            new Thread(() -> {
                cache.put(String.valueOf(finalInt), String.valueOf(finalInt));
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 3; i++) {
            int finalInt = i;
            new Thread(() -> {
                cache.get(String.valueOf(finalInt));
            }, String.valueOf(i)).start();
        }
    }
    // =========================== before print out ===========================
    // 1 线程正在写入...
    // 2 线程正在写入...
    // 3 线程正在写入...
    // 1 线程正在读取...
    // 2 线程正在读取...
    // 3 线程正在读取...
    // 1 线程写入完成
    // 2 线程读取完成，值为：null
    // 2 线程写入完成
    // 3 线程读取完成，值为：null
    // 1 线程读取完成，值为：null
    // 3 线程写入完成
    // =========================== after print out ===========================
    // 2 线程正在写入...
    // 2 线程写入完成
    // 1 线程正在写入...
    // 1 线程写入完成
    // 3 线程正在写入...
    // 3 线程写入完成
    // 1 线程正在读取...
    // 2 线程正在读取...
    // 3 线程正在读取...
    // 1 线程读取完成，值为：1
    // 2 线程读取完成，值为：2
    // 3 线程读取完成，值为：3

}
class Cache{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();


    public void put(String key,Object value){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 线程正在写入...");
            // 暂停一会儿线程
            try { TimeUnit.MICROSECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 线程写入完成");
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    public void get(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 线程正在读取...");
            // 暂停一会儿线程
            try { TimeUnit.MICROSECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            Object res = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 线程读取完成，值为：" + res);
        } finally {
            rwLock.readLock().unlock();
        }

    }

}
