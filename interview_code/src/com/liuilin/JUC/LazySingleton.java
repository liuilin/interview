package com.liuilin.JUC;

/**
 * 懒汉模式
 *
 * @author liuqiang
 * @since 2021-07-29
 */
public class LazySingleton {

    // 这里用 static是因为 getInstance() 方法是静态的，而静态方法不能访问非静态成员变量，所以instance必须是静态成员变量
    // getInstance() 方法是静态是因为构造器是私有的，只能通过 Singleton.getInstance() 方法获取对象实例
    private static LazySingleton instance = null;

    // 构造器是私有是为了防止其他类通过 new Singleton() 来创建对象实例
    private LazySingleton() {
        System.out.println(Thread.currentThread().getName() + " --- 我是构造方法");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new T());
        Thread t2 = new Thread(new T());
        t1.start();
        t2.start();
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

//        for (int i = 1; i <= 10; i++) {
//            new Thread(() -> {
//                LazySingleton.getInstance();
//            }, String.valueOf(i)).start();
//        }
    }
}

class T implements Runnable {

    @Override
    public void run() {
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " " + instance);
    }
}
