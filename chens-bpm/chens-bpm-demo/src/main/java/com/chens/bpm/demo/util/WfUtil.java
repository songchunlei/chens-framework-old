package com.chens.bpm.demo.util;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

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
        System.out.println("发布流程："+definition.getId()+"发布:"+deployment.getId());
        return definition;
    }

    /**
     * 启动
     * @param definitionId
     * @return
     */
    public static ProcessInstance start(RuntimeService runtimeService, String definitionId, Map<String,Object> map){
        ProcessInstance processInstance;
        //启动流程
        if(map!=null)
        {
            processInstance = runtimeService.startProcessInstanceById(definitionId,map);
        }
        else{
            processInstance = runtimeService.startProcessInstanceById(definitionId);
        }

        System.out.println("启动实例："+processInstance.getId());
        return processInstance;
    }

    /**
     * 发布并启动
     * @param xmlResName
     * @return
     */
    public static ProcessInstance deployStart(RepositoryService repositoryService, RuntimeService runtimeService, String xmlResName, Map<String,Object> map){
        //发布
        ProcessDefinition definition = deploy(repositoryService,xmlResName);
        //启动流程
        ProcessInstance processInstance = start(runtimeService,definition.getId(),map);
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

    /**
     * 流程查询任务
     * @param taskService
     * @param processInstanceId
     * @return
     */
    public static List<Task> queryTask(TaskService taskService,String processInstanceId){
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceId(processInstanceId).list();
        for (Task task:taskList) {
            System.out.println("当前任务节点:"+task.getName());
        }
        return taskList;
    }

    /**
     * 自动循环完成task(测试，生产不要用)
     * @param taskService
     * @param processInstanceId
     * @return
     */
    public static List<Task> completeAllTask(TaskService taskService,String processInstanceId){
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceId(processInstanceId).list();
        if(CollectionUtils.isEmpty(taskList))
        {
            return null;
        }
        else
        {
            for (Task task:taskList) {
                System.out.println("当前任务节点:"+task.getName());
                taskService.complete(task.getId());
            }
            //嵌套
            completeAllTask(taskService,processInstanceId);
        }

        return taskList;
    }


}
