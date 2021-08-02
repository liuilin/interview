package com.liuilin.JUC;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum CountryEnum {
    one(1, "齐"),
    two(2, "楚"),
    three(3, "燕"),
    four(4, "赵"),
    five(5, "魏"),
    six(6, "韩");

    private int code;

    private String desc;

    public static CountryEnum valueOf(Integer code) {
        return Arrays.stream(values())
                .filter(v -> v.getCode() == code)
                .findFirst()
                .orElse(null);//.orElseThrow(() -> new IllegalArgumentException("No such enum object for the code : " + code));
    }
}

/**
 * @author liuqiang
 * @since 2021-08-02
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 上完自习，离开图书馆");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 最后关门走人");

        CountDownLatch countDownLatch1 = new CountDownLatch(6);
        // 暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 国被灭");
                countDownLatch1.countDown();
            }, CountryEnum.valueOf(i).getDesc()).start();
        }
        countDownLatch1.await();
        System.out.println(Thread.currentThread().getName() + " 秦国一统天下");
    }
    // =========================== print out ===========================
    // 1 上完自习，离开图书馆
    // 3 上完自习，离开图书馆
    // 2 上完自习，离开图书馆
    // main 最后关门走人
    // 齐 国被灭
    // 楚 国被灭
    // 燕 国被灭
    // 韩 国被灭
    // 赵 国被灭
    // 魏 国被灭
    // main 秦国一统天下
}