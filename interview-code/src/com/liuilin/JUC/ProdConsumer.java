package com.liuilin.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加一个减 1，来 5 轮（相当于两个人操作空调，一个加一度，一个减一度，交替执行 5 次）
 *
 * 多线程模板的企业级口诀：高并发下前提一定是高内聚、低耦合
 * 上联：线程    操作  资源类（解耦，空调自带制冷制热功能，而非人的功能）
 * 下联：判断    干活  唤醒通知
 * 横批：严防多线程下的虚假唤醒
 *
 * @author liuqiang
 * @since 2021-08-03
 */
public class ProdConsumer {

    public static void main(String[] args) {
        ShareData data = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T1").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2").start();

    }

}

class ShareData {

    Lock lock = new ReentrantLock();
    int num = 0;
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (num != 0) {
                // 等待
                condition.await();
            }
            // 干活
            num++;
            System.out.println(Thread.currentThread().getName() + " 生产一个 " + num);
            // 唤醒通知
            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (num == 0) {
                // 等待
                condition.await();
            }
            // 干活
            num--;
            System.out.println(Thread.currentThread().getName() + " 消费一个 " + num);
            // 唤醒通知
            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }
}