package com.liumulin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品购买
 *
 * @author liuqiang
 * @since 2021-07-22
 */
@RestController
public class GoodsController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buy_goods")
    public String buyGoods() {
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
    }
}
