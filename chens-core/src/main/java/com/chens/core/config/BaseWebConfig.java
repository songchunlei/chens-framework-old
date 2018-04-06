package com.chens.core.config;

import com.chens.core.handler.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * 基础Web服务配置
 *
 * @auther songchunlei@qq.com
 * @create 2018/4/6
 */
public abstract class BaseWebConfig {

    /**
     * Feign异常再编码
     * @return
     */
    @Bean
    FeignErrorDecoder getFeignErrorDecoder(){
        return new FeignErrorDecoder();
    }
}
