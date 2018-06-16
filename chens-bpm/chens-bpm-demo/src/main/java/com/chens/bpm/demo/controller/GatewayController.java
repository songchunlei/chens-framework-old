package com.chens.bpm.demo.controller;

import com.chens.bpm.demo.util.WfUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 子流程
 *
 * @author songchunlei@qq.com
 * @create 2018/1/4
 */
@Controller
@RequestMapping("/gateway")
public class GatewayController extends BaseController {


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
     * 流程走向(条件顺序流)
     * 大于等于3天，小于3天
     * 如果只有一条，但是不符合，流程直接结束,那么就要设置默认顺序流，也就是不配置条件
     * @return
     */
    @GetMapping("/flow")
    public ResponseEntity<Result> flow(String days) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("days",days);
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"FlowProcess.bpmn20.xml",map);
        //查询当前网关
        WfUtil.completeAllTask(taskService,processInstance.getId());

        return doSuccess("发布");
    }

    /**
     * 事件网关（没有效果？）
     * @return
     */
    @GetMapping("/event")
    public ResponseEntity<Result> event() {
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"EventGatewayProcess.bpmn20.xml",null);

        runtimeService.signalEventReceived("Greater");

        //查询当前任务
        WfUtil.queryTask(taskService,processInstance.getId());

        return doSuccess("发布");
    }




}
