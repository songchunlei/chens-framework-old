package com.chens.uaa.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/12
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})  //uaa端不需要连接数据库，排除数据库自动配置
@ComponentScan(basePackages={"com.chens"})
@EnableEurekaClient
@EnableFeignClients // feign
//@EnableCircuitBreaker //hystrix熔断
@EnableTransactionManagement // 事务
public class UAAServerApp {
    public static void main(String[] args) {
        SpringApplication.run(UAAServerApp.class, args);
    }
}
