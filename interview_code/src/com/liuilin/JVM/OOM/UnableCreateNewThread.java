package com.liuilin.JVM.OOM;

import java.util.concurrent.TimeUnit;

/**
 * OutOfMemoryError 错误的一种
 *
 * @author liuqiang
 * @since 2021-08-10
 */
public class UnableCreateNewThread {
    public static void main(String[] args) {
        // 没有跳出循环的条件，会一直创建线程
        for (int i = 0; ; i++) {
            System.out.println("i = " + i);
            new Thread(() -> {
                //暂停一会儿线程，让每一个线程都阻塞在这里，诱导发生 OOM
                try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) { e.printStackTrace(); }
            }, ""+i).start();
        }
    }
}
