package com.chens.bpm.demo.error;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * 流程引擎错误
 * @author songchunlei@qq.com
 * @create 2018/1/6
 */
public class ActExceptionDelegate implements JavaDelegate{
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("流程引擎错误类");
        //错误编码，对应<errorEventDefinition errorRef="errCode"></errorEventDefinition>
        throw new BpmnError("errCode");
    }
}
