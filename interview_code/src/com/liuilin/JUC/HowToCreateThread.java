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

        new Thread(new MyRunnable()).start();

        new Thread(new FutureTask<>(new MyCall())).start();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " running...");
        });
        service.shutdown();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " thread running...");
        }, "T1").start();
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }

    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello MyRunnable!");
        }
    }

    static class MyCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("Hello MyCallable!");
            return "success";
        }
    }
}