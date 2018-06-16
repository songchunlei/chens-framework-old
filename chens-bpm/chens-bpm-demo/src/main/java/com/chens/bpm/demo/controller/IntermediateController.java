package com.chens.bpm.demo.controller;

import com.chens.bpm.demo.util.WfUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 中间事件
 *
 * @author songchunlei@qq.com
 * @create 2018/1/8
 */
@Controller
@RequestMapping("/intermediate")
public class IntermediateController extends BaseController{

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
     * 支付成功以后，触发信号
     * 收到信号，生成订单和测试任务
     * 当超过1分钟，会自动转给高级工程师
     * async-executor-activate: true #异步执行
     * @return
     */
    @GetMapping("/order")
    public ResponseEntity<Result> order() throws InterruptedException {

        //启动以后会在act_ru_timer_job上写入数据
        ProcessInstance processInstance = WfUtil.deployStart(repositoryService,runtimeService,"IntermediateProcess.bpmn20.xml",null);

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务："+task.getName());

        taskService.complete(task.getId());

        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        for (Task temp: taskList) {
            System.out.println("当前任务2："+temp.getName());
        }

        return doSuccess("发布");
    }
}
