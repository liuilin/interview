package com.liuilin.JVM;

import java.util.concurrent.TimeUnit;

/**
 * @author Daniel Liu
 * @date 2022-10-24
 */
public class JVMParamsTest {
    public static void main(String[] args) {
        System.out.println("开始运行，可以查看参数了");
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
