package com.chens.cache;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动缓存服务注解
 *
 * @author songchunlei@qq.com
 * @create 2018/5/21
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableMyCache {
}
