package com.chens.cache.redis;

import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;

import java.util.Collection;

/**
 * 自定义RedisCacheManager
 *
 * @author songchunlei@qq.com
 * @create 2018/5/14
 */
public class MyRedisCacheManager extends RedisCacheManager {
    public MyRedisCacheManager(RedisOperations redisOperations) {
        super(redisOperations);
    }

    public MyRedisCacheManager(RedisOperations redisOperations, Collection<String> cacheNames) {
        super(redisOperations, cacheNames);
    }

    public MyRedisCacheManager(RedisOperations redisOperations, Collection<String> cacheNames, boolean cacheNullValues) {
        super(redisOperations, cacheNames, cacheNullValues);
    }
}
