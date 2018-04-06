package com.chens.admin.web.config;

import com.chens.admin.constants.AdminFeignName;
import com.chens.auth.client.interceptor.UserAuthRestInterceptor;
import com.chens.auth.constants.AuthFeignName;
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
 * @auther songchunlei@qq.com
 * @create 2018/3/20
 */
@Configuration
@Primary
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //过滤不鉴权
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();

        //增加用户权限拦截器
        registry.addInterceptor(getUserAuthRestInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
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
                "/"+ AdminFeignName.SYS_USER_RPC+"/findByUsername",
                "/"+AdminFeignName.SYS_DICT_RPC+"/**",
                "/"+AdminFeignName.SYS_LOG_RPC+"/**",
                "/"+ AuthFeignName.SYS_TOKEN_RPC+"/**",
        };
        Collections.addAll(list, urls);
        return list;
    }
}
