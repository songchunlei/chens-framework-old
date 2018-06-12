package com.chens.bpm.demo.controller;

import com.chens.bpm.demo.util.WfUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 工作
 *
 * @author songchunlei@qq.com
 * @create 2018/1/4
 */
@Controller
@RequestMapping("/job")
public class JobController extends BaseController {


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
     * 异步执行
     * async-executor-activate: true #异步执行
     * @return
     */
    @GetMapping("/sync")
    public ResponseEntity<Result> sync() {

        //启动以后会在act_ru_job上写入数据,执行完以后删除
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"SyncProcess.bpmn20.xml");

        return doSuccess("发布");
    }

    /**
     * 定时事件
     * 流程图TimeDuration PT1M
     * async-executor-activate: true #异步执行
     * @return
     */
    @GetMapping("/timer")
    public ResponseEntity<Result> timer(){

        //启动以后会在act_ru_timer_job上写入数据
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"TimerProcess.bpmn20.xml");
        return doSuccess("发布");
    }

    /**
     * 暂停定时事件
     * 流程图TimeDuration PT1M
     * async-executor-activate: true #异步执行
     * @return
     */
    @GetMapping("/suspendTimer")
    public ResponseEntity<Result> supTimer() throws InterruptedException {

        //启动以后会在act_ru_timer_job上写入数据
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"TimerProcess.bpmn20.xml");

        Thread.sleep(10000);

        runtimeService.suspendProcessInstanceById(processInstance.getId());

        Thread.sleep(10000);

        runtimeService.activateProcessInstanceById(processInstance.getId());

        return doSuccess("发布");
    }

    /**
     * 错误事件(无法执行，抛错)
     * async-executor-activate: true #异步执行
     * @return
     */
    @GetMapping("/errorTimer")
    public ResponseEntity<Result> errorTimer() throws InterruptedException {

        //启动以后会在act_ru_timer_job上写入数据
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"errorProcess.bpmn20.xml");

        //查处一个job,设置这个job 再 尝试3次
        Job job = managementService.createJobQuery().singleResult();
        managementService.setJobRetries(job.getId(),3);
        managementService.executeJob(job.getId());

        return doSuccess("发布");
    }

    /**
     * 定时启动（cron表达式，TimerStartEvent 0/5 是每分钟第0秒）
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/timerStart")
    public ResponseEntity<Result> timerStart() throws InterruptedException {

        ProcessDefinition processDefinition = WfUtil.deploy(repositoryService,"TimerStartProcess.bpmn20.xml");

        long dataCount = runtimeService.createProcessInstanceQuery().count();
        System.out.println("变动前："+dataCount);

        //每隔60秒查询一次，查10次
        for (int i = 0; i < 10; i++) {
            Thread.sleep(61000);
            dataCount = runtimeService.createProcessInstanceQuery().count();
            System.out.println("变动后："+dataCount);
        }

        return doSuccess("发布");
    }

    /**
     * 消息启动
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/msgStart")
    public ResponseEntity<Result> msgStart() throws InterruptedException {

        ProcessDefinition processDefinition = WfUtil.deploy(repositoryService,"MsgStartProcess.bpmn20.xml");

        long dataCount = runtimeService.createProcessInstanceQuery().count();
        System.out.println("变动前："+dataCount);

        //发起
        runtimeService.startProcessInstanceByMessage("MsgName");


        dataCount = runtimeService.createProcessInstanceQuery().count();
        System.out.println("变动后："+dataCount);

        return doSuccess("发布");
    }

    /**
     * 错误启动
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/errorStart")
    public ResponseEntity<Result> errorStart() throws InterruptedException {
        //子流程要放在Event SubProcess里
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"MySubProcess.bpmn20.xml");
        return doSuccess("发布");
    }

}
