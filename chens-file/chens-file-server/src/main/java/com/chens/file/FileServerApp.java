package com.chens.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 文件服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/14
 */
@SpringBootApplication
@ComponentScan("com.chens")
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.chens")
public class FileServerApp {
    public static void main(String[] args) {
        SpringApplication.run(FileServerApp.class, args);
    }
}
