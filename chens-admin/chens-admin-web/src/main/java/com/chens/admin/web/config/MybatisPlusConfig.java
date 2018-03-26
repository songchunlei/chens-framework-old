package com.chens.admin.web.config;

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
@MapperScan("com.chens.admin.mapper*")
public class MybatisPlusConfig extends BaseMybatisPlusConfig{


}
