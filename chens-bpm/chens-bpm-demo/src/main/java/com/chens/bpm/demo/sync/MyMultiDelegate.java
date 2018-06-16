package com.chens.bpm.demo.sync;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 处理类
 *
 * @author songchunlei@qq.com
 * @create 2018/1/6
 */
@Component
public class MyMultiDelegate implements JavaDelegate,Serializable {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("处理了一些事情:"+execution.getVariable("testData"));
    }
}
