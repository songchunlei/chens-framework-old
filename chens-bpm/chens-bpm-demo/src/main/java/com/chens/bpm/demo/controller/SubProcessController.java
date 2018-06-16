package com.chens.bpm.demo.controller;

import com.chens.bpm.demo.util.WfUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 子流程
 *
 * @author songchunlei@qq.com
 * @create 2018/1/4
 */
@Controller
@RequestMapping("/sub")
public class SubProcessController extends BaseController {


    /**
     * 存储服务
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 运行时服务
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 流程引擎管理维护
     */
    @Autowired
    private ManagementService managementService;


    /**
     * 任务服务
     */
    @Autowired
    private TaskService taskService;

    /**
     * 错误子流程，边界事件
     * @return
     */
    @GetMapping("/error")
    public ResponseEntity<Result> error() {
        //报错：'boundaryEvent' 中必须包含属性 'attachedToRef',说明边界事件关联的地方没有明确id
        //启动以后会在act_ru_job上写入数据,执行完以后删除
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"ErrorSubProcess.bpmn20.xml",null);
        WfUtil.queryTask(taskService,processInstance.getId());
        return doSuccess("发布");
    }

    /**
     * 主流程引用子流程
     * 用Call Activity组件，Called element里配置对应的子流程名称
     * @return
     */
    @GetMapping("/call")
    public ResponseEntity<Result> call() {

        //发布主，子流程
        ProcessDefinition processDefinition = WfUtil.deploy(repositoryService,"MainProcessCallSubProcess.bpmn20.xml");
        ProcessDefinition subProcessDefinition = WfUtil.deploy(repositoryService,"CallSubProcess.bpmn20.xml");

        //启动主流程
        ProcessInstance processInstance = WfUtil.start(runtimeService,processDefinition.getId(),null);

        //查询节点位置（结果发现主流程的实例只能查到主流程的task）
        WfUtil.completeAllTask(taskService,processInstance.getId());

        //接着查子流程的流程实例
        ProcessInstance subProcessInstance = runtimeService.createProcessInstanceQuery()
                .superProcessInstanceId(processInstance.getId())
                .singleResult();

        //查询节点位置，完成子流程
        WfUtil.completeAllTask(taskService,subProcessInstance.getId());

        return doSuccess("发布");
    }


    /**
     * 将subProcess节点改成adHocSubProcess
     * 特殊子流程，可以选择任意执行任务
     * @return
     */
    @GetMapping("/spec")
    public ResponseEntity<Result> spec() {

        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"SpecSubProcess.bpmn20.xml",null);

        //获取执行流
        Execution execution = runtimeService.createExecutionQuery().
                processInstanceId(processInstance.getId())
                .activityId("SubProcess")
                .singleResult();

        //让流程到达TaskA
        runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"TaskA");

        //查找任务2并完成
        Task subProcessTaskA = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .taskDefinitionKey("TaskA").singleResult();

        //尝试常规查找当前任务
        WfUtil.queryTask(taskService,processInstance.getId());

        taskService.complete(subProcessTaskA.getId());


        //完结
        runtimeService.completeAdhocSubProcess(execution.getId());

        //尝试常规查找当前任务
        WfUtil.queryTask(taskService,processInstance.getId());

        return doSuccess("发布");
    }

}
