package com.chens.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关启动
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/26
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})  //web端不需要连接数据库，排除数据库自动配置
@ComponentScan(basePackages = { "com.chens" })
@EnableFeignClients(basePackages = { "com.chens" })
@EnableEurekaClient
@EnableZuulProxy
public class GatewayApp {
    public static void main(String[] args){
        new SpringApplicationBuilder(GatewayApp.class).web(true).run(args);
    }
}
