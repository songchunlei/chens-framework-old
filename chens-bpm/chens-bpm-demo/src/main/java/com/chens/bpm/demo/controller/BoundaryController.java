package com.chens.bpm.demo.controller;

import com.chens.bpm.demo.util.WfUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 边界事件
 *
 * @author songchunlei@qq.com
 * @create 2018/1/7
 */
@Controller
@RequestMapping("/boundary")
public class BoundaryController extends BaseController{

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
     * 时间边界
     * 当超过1分钟，会自动转给高级工程师
     * async-executor-activate: true #异步执行
     * @return
     */
    @GetMapping("/timer")
    public ResponseEntity<Result> timer() throws InterruptedException {

        //启动以后会在act_ru_timer_job上写入数据
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"TimerBound.bpmn20.xml",null);

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务："+task.getName());

        Thread.sleep(70000);

        Task taskB = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务："+taskB.getName());


        return doSuccess("发布");
    }

    /**
     * 边界信号事件
     * 也可以用于批量收回流程
     * 当超过1分钟，会自动转给高级工程师
     * async-executor-activate: true #异步执行
     * @return
     */
    @GetMapping("/signal")
    public ResponseEntity<Result> signal() {

        //启动以后会在act_ru_timer_job上写入数据
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"SignalBound.bpmn20.xml",null);

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务："+task.getName());

        taskService.complete(task.getId());

        Task taskB = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务："+taskB.getName());

        runtimeService.signalEventReceived("ChangeName");

        Task taskC = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务："+taskC.getName());


        taskService.complete(taskC.getId());

        Task taskD = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务："+taskD.getName());

        return doSuccess("发布");
    }


}
