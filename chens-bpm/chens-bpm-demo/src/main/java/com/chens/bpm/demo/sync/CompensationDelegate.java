package com.chens.bpm.demo.sync;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.delegate.ActivityBehavior;

/**
 * 补偿测试服务
 * Task上的 Is for compensation 要勾起来，表示处理补偿
 * @author songchunlei@qq.com
 * @create 2018/1/7
 */
public class CompensationDelegate implements JavaDelegate{
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("处理了一些补偿事情:"+execution.getProcessInstanceId());
    }
}
