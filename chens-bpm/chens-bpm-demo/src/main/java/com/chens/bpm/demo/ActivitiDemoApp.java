package com.chens.bpm.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动
 *
 * @author songchunlei@qq.com
 * @create 2018/1/3
 */
@SpringBootApplication
@ComponentScan("com.chens")
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.chens")
public class ActivitiDemoApp {
    public static void main(String[] args){
        new SpringApplicationBuilder(ActivitiDemoApp.class).web(true).run(args);

    }
}
