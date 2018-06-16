package com.chens.bpm.demo.sync;

import org.activiti.engine.runtime.Execution;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 自定义方法
 *
 * @author songchunlei@qq.com
 * @create 2018/1/7
 */
@Component
public class MyBean implements Serializable{
    private String name = "scl";

    public String getName() {
        return name;
    }

    /**
     * 将执行流作为参数
     * @param execution
     */
    public void print(Execution execution){
        System.out.println("做了一些事情:"+execution.getProcessInstanceId());
    }
}
