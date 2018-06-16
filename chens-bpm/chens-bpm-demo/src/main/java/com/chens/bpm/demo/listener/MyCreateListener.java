package com.chens.bpm.demo.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 创建任务时就会跑
 * @author songchunlei@qq.com
 * @create 2018/1/6
 */
public class MyCreateListener implements TaskListener {

    /**
     * 这个属性可以通过流程注入
     * <activiti:field name="userName" stringValue="crazyit" />
     * 或者 <activiti:field name="userName"><activiti:expression>${userName}</activiti:expression> </activiti:field>，从流程变量取
     * 或者 expres
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        //通过delegateTask的setOwner，setAssignee,setCan可以分别设置拥有人，代理人，候选人
        System.out.println("Create自定义监听");
    }
}
