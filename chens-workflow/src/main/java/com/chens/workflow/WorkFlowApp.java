package com.chens.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 流程模块
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.chens.workflow*" })
@EnableTransactionManagement
public class WorkFlowApp {
    public static void main(String[] args) {
        SpringApplication.run(WorkFlowApp.class, args);
    }
}
