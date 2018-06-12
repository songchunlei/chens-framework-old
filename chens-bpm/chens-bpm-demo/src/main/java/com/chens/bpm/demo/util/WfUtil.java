package com.chens.bpm.demo.util;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @author songchunlei@qq.com
 * @create 2018/1/6
 */
public class WfUtil {


    /**
     * 发布
     * @param xmlResName
     * @return
     */
    public static ProcessDefinition deploy(RepositoryService repositoryService, String xmlResName){
        //发布
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(xmlResName).deploy();
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        System.out.println("发布流程："+definition.getId());
        return definition;
    }

    /**
     * 发布并启动
     * @param xmlResName
     * @return
     */
    public static ProcessInstance deployStart(RepositoryService repositoryService,RuntimeService runtimeService, String xmlResName){
        //发布
        ProcessDefinition definition = deploy(repositoryService,xmlResName);
        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(definition.getId());
        System.out.println("启动实例："+processInstance.getId());
        return processInstance;
    }


    /**
     * 执行(写死singleResult是因为知道只有一个节点TaskA)
     * @param processInstance
     */
    public static Execution queryExec(RuntimeService runtimeService, ProcessInstance processInstance){
        Execution execution = runtimeService.createExecutionQuery()
                .processInstanceId(processInstance.getId())
                .onlyChildExecutions()
                .singleResult();

        //execution.getActivityId() 当前节点id
        System.out.println(processInstance.getId()+",当前节点:"+execution.getActivityId());
        return execution;
    }
}
