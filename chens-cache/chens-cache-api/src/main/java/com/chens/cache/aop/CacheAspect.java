package com.chens.cache.aop;

import com.chens.cache.service.ICacheService;
import com.chens.cache.annotation.CacheResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 缓存切面
 *
 * @author songchunlei@qq.com
 * @create 2018/5/9
 */
@Aspect
@Component
public class CacheAspect {

    @Autowired
    private ICacheService cacheService;

    @Around("@annotation(cache)")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint, CacheResult cache) throws Throwable {

        String key = cache.key();
        String backupKey = cache.backupKey();


        System.out.println("=========before============");
        proceedingJoinPoint.proceed();
        System.out.println("=========after============");
    }


}
