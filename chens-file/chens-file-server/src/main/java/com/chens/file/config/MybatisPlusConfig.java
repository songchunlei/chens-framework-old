package com.chens.file.config;

import com.chens.core.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-plus配置
 *
 * @auther songchunlei@qq.com
 * @create 2018/2/12
 */
@Configuration
@MapperScan("com.chens.file.mapper*")
public class MybatisPlusConfig extends BaseMybatisPlusConfig{


}
