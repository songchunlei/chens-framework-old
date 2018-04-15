package com.chens.admin.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.chens.core.config.BaseMybatisPlusConfig;

/**
 * Mybatis-plus配置
 *
 * @auther songchunlei@qq.com
 * @create 2018/2/12
 */
@Configuration
@MapperScan(basePackages = {"com.chens.**.mapper"})
public class MybatisPlusConfig extends BaseMybatisPlusConfig{

}
