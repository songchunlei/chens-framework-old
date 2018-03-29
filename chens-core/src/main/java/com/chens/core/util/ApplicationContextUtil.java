package com.chens.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/29
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextUtil.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 根据bean的id来查找对象
     * @param id
     * @return
     */

    public static <T> T getBeanById(String id) {
        return (T) context.getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getBeanByClass(Class c) {
        return (T) context.getBean(c);
    }

}
