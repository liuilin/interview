package com.liuilin.JUC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 *
 * @author liuqiang
 * @since 2021-08-02
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 1; i <= 4; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到停车位");
                    // 暂停一会儿线程
                    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + " 号线程停车 3s 后离开了停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
    // =========================== print out ===========================
    // 1 抢到停车位
    // 2 抢到停车位
    // 2 号线程停车 3s 后离开了停车场
    // 1 号线程停车 3s 后离开了停车场
    // 3 抢到停车位
    // 4 抢到停车位
    // 4 号线程停车 3s 后离开了停车场
    // 3 号线程停车 3s 后离开了停车场
}
