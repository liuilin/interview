package com.liuilin.JUC.singleton;

/**
 * 饿汉式单例
 *
 * @author liuqiang
 * @since 2021-08-11
 */
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton(); // 类加载的时候就初始化了，final 修饰不可更改
/*
    private static final HungrySingleton instance;
    static { instance = new HungrySingleton();}
*/
    public HungrySingleton() { }
    public HungrySingleton getInstance() { return instance; }
}
