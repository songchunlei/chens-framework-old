package com.chens.cache;

import com.chens.cache.vo.CacheKeyWrapper;
import com.chens.cache.vo.CacheWrapper;

/**
 * 缓存接口
 *
 * @author songchunlei@qq.com
 * @author 2018/5/9
 */
public interface ICacheService {

    /**
     * 写缓存
     * @param cacheKey
     * @param result
     * @return
     */
    <T> void put(CacheKeyWrapper cacheKey, CacheWrapper<T> result);

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
