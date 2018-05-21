package com.chens.cache.vo;

import java.io.Serializable;

/**
 * 缓存数据封装
 * 参考 https://github.com/qiujiayu/AutoLoadCache
 * http://www.infoq.com/cn/articles/thinking-about-distributed-cache-redis
 * @author songchunlei@qq.com
 * @create 2018/5/9
 */
public class CacheWrapper<T> implements Serializable, Cloneable  {

    /**
     * 数据
     */
    private T object;

    /**
     * 最后加载时间
     */
    private long lastLoadTime;

    /**
     * 缓存时长
     */
    private int expire;

    /**
     * 判断缓存是否已经过期
     * @return boolean
     */
    public boolean isExpired() {
        if(expire > 0) {
            return (System.currentTimeMillis() - lastLoadTime) > expire * 1000;
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        @SuppressWarnings("unchecked")
        CacheWrapper<T> tmp=(CacheWrapper<T>)super.clone();
        tmp.setObject(this.object);
        return tmp;
    }

    public CacheWrapper(T object, int expire) {
        this.object = object;
        this.lastLoadTime = System.currentTimeMillis();
        this.expire = expire;
    }

    public T getObject() {
        return object;
    }

    public long getLastLoadTime() {
        return lastLoadTime;
    }

    public int getExpire() {
        return expire;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

}
