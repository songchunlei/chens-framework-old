package com.chens.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义缓存注解-获取缓存数据
 *
 * @author songchunlei@qq.com
 * @create 2018/5/9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface CacheResult {

    /**
     * 缓存key
     * @return
     */
    String key();

    /**
     * 从缓存名称取
     * @return
     */
    String name();

    /**
     * 缓存备份
     * @return
     */
    String backupKey() default "";

    /**
     * 是否用分布式锁（事务）
     * @return
     */
    boolean needLock() default false;
}
