package com.chens.bpm.demo.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 创建任务时就会跑
 * @author songchunlei@qq.com
 * @create 2018/1/6
 */
public class MyCompleteListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("Complete自定义监听");
    }
}
