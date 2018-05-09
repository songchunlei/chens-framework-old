package com.chens.cache.vo;

import java.io.Serializable;

/**
 * 缓存key
 * 参考 https://github.com/qiujiayu/AutoLoadCache
 * http://www.infoq.com/cn/articles/thinking-about-distributed-cache-redis
 * @author songchunlei@qq.com
 * @create 2018/5/9
 */
public final class CacheKeyWrapper implements Serializable{

    /**
     * 缓存名
     */
    private final String cacheName;

    /**
     * 空间,可以用项目名，防止多项目缓存key重复
     */
    private final String namespace;

    /**
     * 缓存key
     */
    private final String key;


    public CacheKeyWrapper(String cacheName,String namespace, String key) {
        this.cacheName = cacheName;
        this.namespace=namespace;
        this.key=key;
    }

    public String getCacheKey() {
        if(null != this.namespace && this.namespace.length() > 0) {
            return new StringBuilder(this.namespace).append(":").append(this.key).toString();
        }
        return this.key;
    }

    public String getLockKey() {
        StringBuilder key=new StringBuilder(getCacheKey());
        key.append(":lock");
        return key.toString();
    }

    public String getCacheName() {
        return cacheName;
    }
}
