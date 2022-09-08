package com.liuilin.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuqiang
 * @since 2021-08-03
 */
public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(5));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 生产线程启动");
            try {
                myResource.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Product").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 消费线程启动");
            try {
                myResource.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        myResource.stop();
        System.out.println(Thread.currentThread().getName() + " --- 5s 时间到，main 线程叫停一切");
    }
    // ============================== print out =============================
    // Product 生产线程启动
    // Product 开始生产，0 插入成功
    // Consumer 消费线程启动
    // Consumer 消费蛋糕队列成功，值为：0
    // Product 开始生产，1 插入成功
    // Consumer 消费蛋糕队列成功，值为：1
    // Product 开始生产，2 插入成功
    // Consumer 消费蛋糕队列成功，值为：2
    // Product 开始生产，3 插入成功
    // Consumer 消费蛋糕队列成功，值为：3
    // Product 开始生产，4 插入成功
    // Consumer 消费蛋糕队列成功，值为：4
    // main --- 5s 时间到，main 线程叫停一切
    // Product 停止生产
    // Consumer 超过 2s 没有获取到蛋糕，消费退出并停止获取
}

class MyResource {

    BlockingQueue<String> blockingQueue;
    // 默认开启生产和消费，volatile 保证生产后别的线程里面可见
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void product() throws InterruptedException {
        // 判断、干活、唤醒线程
        String data;
        while (FLAG) {
            // 不能这么写，这是一个 while 循环，会导致一直不停的创建对象
            // String data = atomicInteger.getAndIncrement() + "";
            data = atomicInteger.getAndIncrement() + "";
            boolean res = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (res) {
                System.out.println(Thread.currentThread().getName() + " 开始生产，" + data + " 插入成功");
            } else {
                System.out.println(Thread.currentThread().getName() + " 开始生产，" + data + " 插入失败");
            }
            // 1s 生产一个，挂起线程切换消费线程
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + " 停止生产");
    }

    public void consume() throws InterruptedException {
        String res;
        while (FLAG) {
            // 不能这么写，这是一个 while 循环，会导致一直不停的创建对象
            // String res = blockingQueue.poll(2L, TimeUnit.SECONDS);
            res = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (res == null || "".equalsIgnoreCase(res)) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + " 超过 2s 没有获取到蛋糕，消费退出并停止获取");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " 消费蛋糕队列成功，值为：" + res);
        }
    }

    public void stop() {
        FLAG = false;
    }

}