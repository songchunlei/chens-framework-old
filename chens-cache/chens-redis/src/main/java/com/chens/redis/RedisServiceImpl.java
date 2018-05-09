package com.chens.redis;

import com.chens.cache.ICacheService;
import com.chens.cache.vo.CacheKeyWrapper;
import com.chens.cache.vo.CacheWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * redis缓存实现
 *
 * @author songchunlei@qq.com
 * @create 2018/5/9
 */
@Service("redisService")
public class RedisServiceImpl implements ICacheService{

    @Autowired
    private CacheManager cacheManager;


    @Override
    public <T> void put(CacheKeyWrapper cacheKey, CacheWrapper<T> result) {
        cacheManager.getCache(cacheKey.getCacheName()).put(cacheKey.getCacheKey(), result);
    }

    @Override
    public <T> T get(CacheKeyWrapper key) {
        Cache.ValueWrapper valueWrapper = cacheManager.getCache(key.getCacheKey()).get(key);
        if(valueWrapper == null)
        {
            return null;
        }
        else
        {
            CacheWrapper<T> cacheWrapper = (CacheWrapper<T>) valueWrapper.get();
            //判断是否过期，过期则不取缓存
            if(cacheWrapper.isExpired())
            {
                return null;
            }
            return cacheWrapper.getObject();
        }
    }

    @Override
    public void delete(CacheKeyWrapper cacheKeyWrapper) {
        cacheManager.getCache(cacheKeyWrapper.getCacheName()).evict(cacheKeyWrapper.getCacheKey());
    }
}
