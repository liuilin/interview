package com.liumulin.controller;

import java.time.Duration;
import java.util.UUID;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品购买
 *
 * @author liuqiang
 * @since 2021-07-22
 */
@RestController
public class GoodsController {

    public static final String REDIS_LOCK = "REDIS_LOCK";
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;


    @GetMapping("/buy_goods")
    public String buyGoods() throws Exception {
        String value = UUID.randomUUID() + Thread.currentThread().getName();
        System.out.println("value = " + value);

        RLock rLock = redisson.getLock(REDIS_LOCK);
        rLock.lock();
        try {
//            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10, TimeUnit.SECONDS);
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, Duration.ofSeconds(10));
            if (!flag) {
                return "抢锁失败";
            }

            String res = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNum = res == null ? 0 : Integer.parseInt(res);

            if (goodsNum > 0) {
                int realGoodsNum = goodsNum - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realGoodsNum));
                System.out.println("你已经成功秒杀商品，此时还剩余：" + realGoodsNum + "件" + "\t 服务器端口: " + serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realGoodsNum + "件" + "\t 服务器端口: " + serverPort;
            } else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
            }
            return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
        } finally {
            // 增强程序健壮性 避免错误 attempt to unlock lock, not locked by current thread node id
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }

        // 换用上面的最终分布式锁 redisson
/*
        String value = UUID.randomUUID() + Thread.currentThread().getName();
        System.out.println("value = " + value);
        try {
//            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10, TimeUnit.SECONDS);
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, Duration.ofSeconds(10));
            if (!flag) {
                return "抢锁失败";
            }

            String res = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNum = res == null ? 0 : Integer.parseInt(res);

            if (goodsNum > 0) {
                int realGoodsNum = goodsNum - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realGoodsNum));
                System.out.println("你已经成功秒杀商品，此时还剩余：" + realGoodsNum + "件" + "\t 服务器端口: " + serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realGoodsNum + "件" + "\t 服务器端口: " + serverPort;
            } else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
            }
            return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
        } finally {
//            if (value.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(REDIS_LOCK))) {
//                stringRedisTemplate.delete(REDIS_LOCK);
//            }
            // 上面代码不能保证原子操作
            // 1.采用 Redis 事务解决
*/
/*
            while (true) {
                // 加事务，乐观锁
                stringRedisTemplate.watch(REDIS_LOCK);
                if (value.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(REDIS_LOCK))) {
                    stringRedisTemplate.setEnableTransactionSupport(true);
                    stringRedisTemplate.multi();
                    stringRedisTemplate.delete(REDIS_LOCK);
                    List<Object> list = stringRedisTemplate.exec();
                    // 如果为 null，则是删除失败，返回重新执行
                    if (CollectionUtils.isEmpty(list)) {
                        continue;
                    }
                    // 如果删除成功，释放监视器
                    stringRedisTemplate.unwatch();
                    break;
                }
            }
*//*

            // 2.采用官方推荐的 Lua 脚本
*/
/*
            Jedis jedis = RedisUtil.getJedis();
            String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n"
                    + "    return redis.call(\"del\",KEYS[1])\n"
                    + "else\n"
                    + "    return 0\n"
                    + "end";
            try {
                Object res = jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
                if ("1".equals(res.toString())) {
                    System.out.println("---delete REDIS_LOCK success");
                } else {
                    System.out.println("---delete REDIS_LOCK error");
                }
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
*//*

        }
*/
    }
}
