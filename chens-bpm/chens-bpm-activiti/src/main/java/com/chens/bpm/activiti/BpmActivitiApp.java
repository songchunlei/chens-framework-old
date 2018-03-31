package com.chens.bpm.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/30
 */
@SpringBootApplication
@ComponentScan("com.chens")
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.chens")
public class BpmActivitiApp {
    public static void main(String[] args) {
        SpringApplication.run(BpmActivitiApp.class, args);
    }
}
