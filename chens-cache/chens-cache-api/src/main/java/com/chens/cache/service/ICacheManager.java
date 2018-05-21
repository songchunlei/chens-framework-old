package com.chens.cache.service;

import com.chens.cache.vo.CacheKeyWrapper;
import com.chens.cache.vo.CacheWrapper;

/**
 * 缓存管理器
 *
 * @author songchunlei@qq.com
 * @create 2018/5/15
 */
public interface ICacheManager {

    /**
     * 写缓存
     * @param cacheKey
     * @param result
     * @return
     */
    <T> void set(CacheKeyWrapper cacheKey, CacheWrapper<T> result);

    /**
     * 取缓存
     * @param key
     * @return
     */
    <T> T get(final CacheKeyWrapper key);


    /**
     * 删除缓存
     * @param key
     * @return
     */
    void delete(final CacheKeyWrapper key);
}
