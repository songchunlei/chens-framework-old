package com.chens.admin.web.config;

import com.chens.admin.constants.AdminFeignName;
import com.chens.auth.client.interceptor.UserAuthRestInterceptor;
import com.chens.auth.constants.AuthFeignName;
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
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
        /*
            增加服务权限烂机器
         */
        //registry.addInterceptor(getServiceAuthRestInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        /*
            增加用户权限拦截器
         */
        registry.addInterceptor(getUserAuthRestInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        super.addInterceptors(registry);
    }

    /**
     * 配置服务权限拦截
     * @return
     */
    /*
    @Bean
    ServiceAuthRestInterceptor getServiceAuthRestInterceptor() {
        return new ServiceAuthRestInterceptor();
    }
    */

    /**
     * 配置用户用户token拦截
     * @return
     */
    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/authController/**",
                "/sysTokenRpc/**",
                "/"+ AdminFeignName.SYS_USER_RPC+"/findByUsername",
                "/"+ AuthFeignName.SYS_TOKEN_RPC+"/**"
        };
        Collections.addAll(list, urls);
        return list;
    }
}
