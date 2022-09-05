package com.liuilin.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * @author liuqiang
 * @since 2021-08-03
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        soft();
    }

    private static void soft() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
    }

    private static void array1() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
        blockingQueue.put("a");
        blockingQueue.put("b");
        System.out.println("------");
//        blockingQueue.put("c");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
    }

    private static void linked() {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(2);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue);
    }

    private static void array() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.add("c"));
    }

}
