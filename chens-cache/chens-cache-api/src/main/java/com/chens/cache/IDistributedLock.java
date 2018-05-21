package com.chens.cache;

/**
 * 自定义分布式锁
 *
 * @author songchunlei@qq.com
 * @create 2018/5/14
 */
public interface IDistributedLock {

    /**
     * 获取锁
     * @return
     */
    boolean getLock();

    /**
     * 释放锁
     */
    void releaseLock();

}
