package com.liuilin.JUC;

import java.util.concurrent.TimeUnit;

/**
 * 死锁 死锁是指两个或两个以上的进程在执行过程中， 因争夺资源而造成的一种 <font color='red'> 互相等待 </font> 的现象 若无外力干涉那它们都将无法推进下去
 *
 * @author liuqiang
 * @since 2021-08-04
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        new Thread(new ThreadLockHolder("lockA", "lockB"), "T1").start();
        new Thread(new ThreadLockHolder("lockB", "lockA"), "T2").start();
    }

}

class ThreadLockHolder implements Runnable {

    String lock1;
    String lock2;

    public ThreadLockHolder(String lockA, String lockB) {
        this.lock1 = lockA;
        this.lock2 = lockB;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " 自己持有：" + lock1 + "，尝试获得：" + lock2);
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " 自己持有：" + lock2 + "，尝试获得：" + lock1);
            }
        }
    }
}
//    public static void main(String[] args) {
//        String o1 = "lockA";
//        String o2 = "lockB";
//        new Thread(() -> {
//            synchronized (o1) {
////                System.out.println(Thread.currentThread().getName()+" ");
//                System.out.println(Thread.currentThread().getName() + " 自己持有：" + o1 + "尝试获得：" + o2);
//                // 暂停一会儿线程
//                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//                synchronized (o2) {
//                    System.out.println("o2 execute...");
//                }
//            }
//        }, "T1").start();
//
//        new Thread(() -> {
//            synchronized (o2) {
//                System.out.println(Thread.currentThread().getName() + " 自己持有：" + o2 + "尝试获得：" + o1);
//                // 暂停一会儿线程
//                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//                synchronized (o1) {
//                    System.out.println("o1 execute...");
//                }
//            }
//        }, "T2").start();
//    }