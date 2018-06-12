package com.chens.bpm.demo.controller;

import com.chens.bpm.demo.util.WfUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * 流程操作类测试
 * UserTask用Complete
 * ReceiveTask用Trigger
 *
 * @author songchunlei@qq.com
 * @create 2018/6/9
 */
@Controller
@RequestMapping("/process")
public class ProcessController extends BaseController{

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
     * 任务服务
     */
    @Autowired
    private TaskService taskService;

    /**
     * 用消息启动
     * msgStart
     * @return
     */
    @GetMapping("/msgStart")
    public ResponseEntity<Result> msgStart(String msg) throws IOException {
        runtimeService.startProcessInstanceByMessage(msg);
        return doSuccess("发布");
    }

    /**
     * 并行
     * 有一个主执流，有多少个子节点，就有多少个子执行流
     * @return
     */
    @GetMapping("/parallel")
    public ResponseEntity<Result> parallel(String msg) throws IOException {

        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MyParallelProcess.bpmn20.xml");
        //处理task
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
        for (Task task:taskList) {
            //获取task所在的执行流
            Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
            if("TaskA".equals(task.getName())){
                runtimeService.setVariableLocal(execution.getId(),"taskVarA","varA");
            }
            else{
                runtimeService.setVariable(execution.getId(),"taskVarB","varB");
            }
        }
        //完成所有任务
        for (Task task:taskList) {
            taskService.complete(task.getId());
        }

        //写死singleResult是因为知道只有一个节点TaskC
        Task taskC = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println(runtimeService.getVariableLocal(processInstance.getId(),"taskVarA"));
        System.out.println(runtimeService.getVariable(processInstance.getId(),"taskVarA"));
        System.out.println(runtimeService.getVariableLocal(processInstance.getId(),"taskVarB"));
        System.out.println(runtimeService.getVariable(processInstance.getId(),"taskVarB"));
        System.out.println(processInstance.getId());
        return doSuccess("发布");
    }

    /**
     * 接收节点（Receive Task，触发类节点，需要用trigger推进）
     * @return
     */
    @GetMapping("/receiveTask")
    public ResponseEntity<Result> mailTask() throws IOException {

        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MyReceive.bpmn20.xml");
        //处理task
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();

        //查询执行流(子执行流),写死singleResult是因为知道只有一个节点TaskA
        Execution execution = WfUtil.queryExec(runtimeService,processInstance);

        //触发等待节点。让流程往前走
        runtimeService.trigger(execution.getId());

        //再查一次，execution.getActivityId() 当前节点id
        Execution executionB = WfUtil.queryExec(runtimeService,processInstance);


        //触发节点。让流程往前走(看看UserTask节点能不能用trigger)
        //会报错 UserTask should not be signalled before complete
        runtimeService.trigger(executionB.getId());

        //再查一次，execution.getActivityId() 当前节点id
        Execution executionC = WfUtil.queryExec(runtimeService,processInstance);
        return doSuccess("发布");
    }

    /**
     * 触发信号事件
     * Signal可以很多流程共用一个signal，然后等待同一个通知，同时推进
     * 捕获事件:等通知再走
     * 抛出事件：流程抛出事件，直接往下走
     * @return
     */
    @GetMapping("/signalTask")
    public ResponseEntity<Result> signalTask() throws IOException {


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"Signal.bpmn20.xml");

        //查询执行流(子执行流)
        Execution execution = WfUtil.queryExec(runtimeService,processInstance);

        //触发等待节点。让流程往前走
        runtimeService.signalEventReceived("SignalDefTest");

        //再查一次，execution.getActivityId() 当前节点id
        Execution executionB = WfUtil.queryExec(runtimeService,processInstance);

        return doSuccess("发布");
    }

    /**
     * 触发消息信号事件
     * MsgReceive，要指定流程实例
     * 捕获事件:等通知再走
     * 抛出事件：流程抛出事件，直接往下走
     * @return
     */
    @GetMapping("/msgReceTask")
    public ResponseEntity<Result> msgTask() throws IOException {


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MessageSignal.bpmn20.xml");

        //查询执行流(子执行流)
        Execution execution = WfUtil.queryExec(runtimeService,processInstance);

        //触发等待节点。让流程往前走
        runtimeService.messageEventReceived("MsgDefTest",execution.getId());

        //再查一次，execution.getActivityId() 当前节点id
        Execution executionB = WfUtil.queryExec(runtimeService,processInstance);

        return doSuccess("发布");
    }







}
