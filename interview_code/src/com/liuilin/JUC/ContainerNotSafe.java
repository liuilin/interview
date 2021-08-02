package com.liuilin.JUC;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liuqiang
 * @since 2021-08-02
 */
public class ContainerNotSafe {

    /**
     * 1. 故障现象
     *  java.util.ConcurrentModificationException
     * 2. 导致原因
     *  并发争抢修改导致，参考我们的签名情况。
     *  一个人正在写入，另外一个人过来抢夺导致数据不一致异常。并发修改异常。
     * 3. 解决方案
     *  3.1 new Vector<>（）；
     *  3.2 Collections. synchronizedList（new ArrayList<>（））；
     *  3.3 new CopyOnWriteArrayList（）；
     * 4. 优化建议（同样的错误不犯第2次）
     * */
    public static void main(String[] args) {
        // List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
