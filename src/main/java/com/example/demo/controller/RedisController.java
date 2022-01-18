package com.example.demo.controller;

import com.example.demo.common.support.RedisKit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author banyue
 * date 2020-03-03
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    // redis中存储的过期时间60s
    private static int ExpireTime = 60;


    @Resource
    private RedisKit redisUtil;

    @RequestMapping("set")
    public boolean redisSet(@RequestParam("key") String key, @RequestParam("value")String value){
        return redisUtil.set(key, value, ExpireTime);
    }

    @RequestMapping("get")
    public Object redisget(@RequestParam("key") String key) {
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    @ResponseBody
    public boolean expire(@RequestParam("key") String key){
        return redisUtil.expire(key,ExpireTime);
    }

}
