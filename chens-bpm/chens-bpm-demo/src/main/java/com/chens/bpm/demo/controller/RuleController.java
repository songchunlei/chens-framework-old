package com.chens.bpm.demo.controller;

import com.chens.bpm.demo.entity.Member;
import com.chens.bpm.demo.util.KieUtils;
import com.chens.bpm.demo.util.WfUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.rules.RulesHelper;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.drools.KnowledgeBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规则引擎
 * @author songchunlei@qq.com
 * @create 2018/1/9
 */
@Controller
@RequestMapping("/rule")
public class RuleController extends BaseController{

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
     * 测试
     * activation-group 一个组里只有一个生效
     * salience 优先级
     * @return
     */
    @GetMapping("/test")
    public ResponseEntity<Result> test() {
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();
        Member member = new Member("scl",Member.GOLD,new BigDecimal(120));
        Member member2 = new Member("scl2",Member.COOPER,new BigDecimal(130));
        kieSession.insert(member);
        kieSession.insert(member2);
        int count = kieSession.fireAllRules();

        System.out.println(member.toString());
        System.out.println(member2.toString());

        kieSession.dispose();
        return doSuccess("激活"+count+"条规则");
    }

    /**
     * 流程-规则任务Rule Task
     * 需要部署规则文件到act_re_ge表
     * deployment 57501 doesn't contain any rules?? 问题待解决
     * @return
     */
    @GetMapping("/task")
    public ResponseEntity<Result> task() {
        //发布
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("RuleProcess.bpmn20.xml")
                .addClasspathResource("condition.drl").deploy();
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        System.out.println("发布流程："+definition.getId()+"发布:"+deployment.getId());

        ProcessInstance processInstance = WfUtil.start(runtimeService,definition.getId(),null);
        Map<String,Object> maps = new HashMap<>(1);
        maps.put("member",new Member("scl",Member.GOLD,new BigDecimal(1000)));
        //完成第一个任务
        WfUtil.queryTaskAndComplete(taskService,processInstance.getId(),maps);

        return doSuccess("发布");
    }



}
