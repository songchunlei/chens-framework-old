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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MyParallelProcess.bpmn20.xml",null);
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

        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MyReceive.bpmn20.xml",null);
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


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"Signal.bpmn20.xml", null);

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


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MessageSignal.bpmn20.xml",null);

        //查询执行流(子执行流)
        Execution execution = WfUtil.queryExec(runtimeService,processInstance);

        //触发等待节点。让流程往前走
        runtimeService.messageEventReceived("MsgDefTest",execution.getId());

        //再查一次，execution.getActivityId() 当前节点id
        Execution executionB = WfUtil.queryExec(runtimeService,processInstance);

        return doSuccess("发布");
    }

    /**
     * 测试exec监听流程
     * @return
     */
    @GetMapping("/execListener")
    public ResponseEntity<Result> execListener() {


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"ExecListenerProcess.bpmn20.xml",null);

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

        //完成任务
        taskService.complete(task.getId());

        return doSuccess("发布");
    }

    /**
     * 测试监听流程
     * @return
     */
    @GetMapping("/listener")
    public ResponseEntity<Result> listener() {


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"ListenerProcess.bpmn20.xml",null);

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

        //指定代理人
        taskService.setAssignee(task.getId(),"1");
        System.out.println("已指定代理人：user:1");

        //完成任务
        taskService.complete(task.getId());
        return doSuccess("发布");
    }




    /**
     * 重复任务
     * Multi-instance type  : 重复执行类型
     * 1。 设置Cardinality就是循环次数
     *
     * 2。 Collection对应的是循环的数组，根据这个数组循环几次
     *     Element variable:就是循环时取值
     * @return
     */
    @GetMapping("/multi")
    public ResponseEntity<Result> multi() {

        List<String> testDataList = new ArrayList<>(2);
        testDataList.add("a");
        testDataList.add("b");
        Map<String,Object> map = new HashMap<>(1);
        map.put("testDataList",testDataList);

        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MultiProcess.bpmn20.xml",map);



        return doSuccess("发布");
    }

    /**
     * 重复任务
     * 测试内部参数(虽然定义了循环5次，但是循环完成条件设置成了2次就跳出循环)
     * nrOfCompletedInstances 已完成的实例数
     * nrOfInstances 实例总数
     * nrOfActiveInstances 当前正在执行的实例数
     * loopCounter 当前循环的索引
     * 这些参数也可以在execution.getVariable("xxx")获取
     * @return
     */
    @GetMapping("/multi2")
    public ResponseEntity<Result> multi2() {


        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MultiProcess2.bpmn20.xml",null);



        return doSuccess("发布");
    }






}
