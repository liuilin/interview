package com.liuilin.JUC;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author liuqiang
 * @since 2021-07-31
 */
public class HowToCreateThread {

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable(), "Runnable").start();
        new Thread(new FutureTask<>(new MyCallable()), "Callable").start();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " running...");
        });
        service.shutdown();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " thread running...");
        }, "RunnableWithLambda").start();
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }

    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            return 1;
        }
    }
}