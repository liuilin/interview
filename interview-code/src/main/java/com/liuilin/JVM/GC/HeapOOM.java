package com.liuilin.JVM.GC;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 内存溢出排查
 *
 * @author liuqiang
 * @since 2021-08-06
 */
public class HeapOOM {

    byte[] buffer = new byte[1024 * 1024]; // 创建 1M 文件

    public static void main(String[] args) {
        List<HeapOOM> list = new ArrayList<>();
        Date date = new Date();
        for (int i = 1; i <= 100; i++) {
            list.add(new HeapOOM());
            // 暂停一会儿线程
            try { TimeUnit.MICROSECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println("数据添加完毕");
        new Scanner(System.in).next();
        list = null;
        date = null;
        System.out.println("list and date Object 已清空，请继续操作");
        new Scanner(System.in).next();
        System.out.println("已结束");
    }
}
