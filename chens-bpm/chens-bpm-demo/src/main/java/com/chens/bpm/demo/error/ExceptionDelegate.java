package com.chens.bpm.demo.error;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * 错误
 * @author songchunlei@qq.com
 * @create 2018/1/6
 */
public class ExceptionDelegate implements JavaDelegate{
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("处理类");
        throw new RuntimeException("错误");
    }
}
