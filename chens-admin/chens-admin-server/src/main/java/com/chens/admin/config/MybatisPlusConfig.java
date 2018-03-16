package com.chens.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import com.chens.core.config.BaseMybatisPlusConfig;

/**
 * Mybatis-plus配置
 *
 * @auther songchunlei@qq.com
 * @create 2018/2/12
 */
@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
@MapperScan("com.chens.admin.web.mapper*")
public class MybatisPlusConfig extends BaseMybatisPlusConfig{


}
