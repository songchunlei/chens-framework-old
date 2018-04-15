package com.chens.bpm.config;

import com.chens.auth.client.interceptor.UserAuthRestInterceptor;
import com.chens.core.handler.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 拦截器配置
 *
 * @author songchunlei@qq.com
 * @create 2018/3/20
 */
@Configuration
@Primary
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();

        //增加用户权限拦截器
        registry.addInterceptor(getUserAuthRestInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        //存入缓存
        super.addInterceptors(registry);
    }

    /**
     * 配置用户用户token拦截
     * @return
     */
    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    /**
     * Feign异常再编码
     * @return
     */
    @Bean
    FeignErrorDecoder getFeignErrorDecoder(){
        return new FeignErrorDecoder();
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/authController/**"
        };
        Collections.addAll(list, urls);
        return list;
    }
}
