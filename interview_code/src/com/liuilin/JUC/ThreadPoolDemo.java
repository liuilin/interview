package com.liuilin.JUC;

import java.util.concurrent.*;

/**
 * @author liuqiang
 * @since 2021-08-03
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                // new ThreadPoolExecutor.AbortPolicy()
                // new ThreadPoolExecutor.CallerRunsPolicy()
                // new ThreadPoolExecutor.DiscardPolicy()
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        // 池化技术先关池，再操作业务
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " 办理业务"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void jdkThreadPool() {
        //        ExecutorService threadPool = Executors.newFixedThreadPool(5); // 一池 5 个处理线程（银行 5 个处理业务窗口）
//        ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 一池 1 个处理线程（只有一个工作人员上班）
        ExecutorService threadPool = Executors.newCachedThreadPool(); // 一池 N 线程，随机扩容多线程

        // 池化技术先关池，再操作业务
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " 办理业务"));
                // 暂停一会儿线程
                try {
                    TimeUnit.MICROSECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
