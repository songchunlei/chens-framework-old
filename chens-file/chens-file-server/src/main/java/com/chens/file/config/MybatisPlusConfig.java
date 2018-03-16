package com.chens.file.config;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import com.chens.core.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-plus配置
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/14
 */
@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
@MapperScan("com.chens.file.mapper*")
public class MybatisPlusConfig extends BaseMybatisPlusConfig{


}
