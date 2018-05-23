package com.chens.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动
 *
 * @auther songchunlei@qq.com
 * @create 2018/2/11
 */
@SpringBootApplication
@ComponentScan("com.chens")
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.chens")
public class AdminWebApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApp.class, args);
    }
}
