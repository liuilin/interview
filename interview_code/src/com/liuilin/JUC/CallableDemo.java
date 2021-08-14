package com.liuilin.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author liuqiang
 * @since 2021-08-03
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 两个线程：1. main 2. FutureTask
        FutureTask futureTask = new FutureTask<>(new MyCallable());
        FutureTask futureTask1 = new FutureTask<>(new MyCallable());
        new Thread(futureTask, "FutureTask").start();
//        new Thread(futureTask, "FutureTask1").start(); // 同样的执行应该复用，不应该再计算第二次。所以 Callable 不会打印第二次
        new Thread(futureTask1, "FutureTask").start(); // 用多个 FutureTask 才会打印两次
//        Integer res2 = (Integer) futureTask.get(); // 阻塞，等待线程执行完才走 res1 打印

        Integer res1 = 100;
//        while (!futureTask.isDone()) { }
        System.out.println("先执行 futureTask，结果为：" + res1);
        // 要求获得 Callable 线程的计算结果，如果没有计算完成就要去抢，会导致阻塞，得把值结算完成
        Integer res2 = (Integer) futureTask.get(); // 不会阻塞，先打印 res1，再计算结果并等待线程返回
        System.out.println(res1 + res2);
    }
    // ================================ print out ================================
    // 先执行 futureTask，结果为：100
    // FutureTask running...
    // FutureTask running...
    // 1100

}

class MyCallable implements Callable {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " running...");
        // 暂停一会儿线程
        TimeUnit.SECONDS.sleep(3);
        return 1000;
    }
}