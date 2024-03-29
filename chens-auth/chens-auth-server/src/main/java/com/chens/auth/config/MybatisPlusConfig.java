package com.chens.auth.config;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import com.chens.core.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-plus配置
 *
 * @author songchunlei@qq.com
 * @create 2018/2/12
 */
@Configuration
@MapperScan(basePackages = {"com.chens.**.mapper"})
public class MybatisPlusConfig extends BaseMybatisPlusConfig{


}
