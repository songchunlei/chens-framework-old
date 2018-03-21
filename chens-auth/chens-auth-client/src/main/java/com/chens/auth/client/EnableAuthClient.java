package com.chens.auth.client;

import com.chens.auth.client.configuration.AuthAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动鉴权注解
 * @auther songchunlei@qq.com
 * @create 2018/3/20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthAutoConfiguration.class)
@Documented
@Inherited
public @interface EnableAuthClient {
}
