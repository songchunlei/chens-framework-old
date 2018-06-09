package com.chens.bpm.demo.config;

import com.chens.core.config.BaseMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = {"com.chens.**.mapper"})
public class MybatisPlusConfig extends BaseMybatisPlusConfig{

}
