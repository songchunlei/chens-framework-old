package com.chens.bpm.demo.sync;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * 处理类
 *
 * @author songchunlei@qq.com
 * @create 2018/1/6
 */
public class MyJavaDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("处理了一些事情");
    }
}
