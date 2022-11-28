package com.liuilin.spring.setInjection;

/**
 * 循环依赖
 *
 * @author Daniel Liu
 * @date 2022-10-20
 */
public class SetDependencies {
    public static void main(String[] args) {
        // 完成了实例化和初始化操作
        ServiceAA a = new ServiceAA();
        ServiceBB b = new ServiceBB();
        // 把 a 属性注入 b 中
        b.setServiceAA(a);
        // 把 b 属性注入 a 中
        a.setServiceBB(b);
    }
}
