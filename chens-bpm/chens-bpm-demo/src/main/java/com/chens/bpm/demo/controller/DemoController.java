package com.chens.bpm.demo.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.vo.PageVo;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试服务(用GetMapping是为了测试省事，)
 *
 * @author songchunlei@qq.com
 * @create 2018/1/3
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

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
     * 流程引擎管理维护
     */
    @Autowired
    private ManagementService managementService;

    /**
     * 历史数据
     */
    @Autowired
    private HistoryService historyService;

    /**
     * 动态模型修改（不发布对流程模型修改）
     */
    private DynamicBpmnService dynamicBpmnService;

    /**
     * 测试发布
     * @return
     */
    @GetMapping("/deploy")
    public ResponseEntity<Result> getMyTodTaskPage(){

        //发布
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("MyVacation.bpmn20.xml").deploy();

        return doSuccess(deployment.getKey());
    }

    /**
     * 启动流程
     */
    @GetMapping("/start")
    public ResponseEntity<Result> start(){

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("MyVacation");

        return doSuccess(processInstance.getId()+" "+processInstance.getBusinessKey() );
    }


    /**
     * 根据流程实例id查询
     */
    @GetMapping("/query")
    public ResponseEntity<Result> query(String id){
        Task task = taskService.createTaskQuery().processInstanceId(id).singleResult();
        return doSuccess("节点id："+task.getId());
    }

    /**
     * 审核
     */
    @GetMapping("/audit")
    public ResponseEntity<Result> audit(String taskId){
        taskService.complete(taskId);
        return doSuccess("审批节点"+taskId);
    }




}
