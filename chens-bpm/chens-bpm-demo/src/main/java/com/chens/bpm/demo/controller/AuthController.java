package com.chens.bpm.demo.controller;

import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 授权
 *
 * @author songchunlei@qq.com
 * @create 2018/6/9
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
    /**
     * 存储服务
     */
    @Autowired
    private RepositoryService repositoryService;


    /**
     * 指定用户能启动(存act_ru_identitylink中间表)
     * @return
     */
    @GetMapping("/authUser")
    public ResponseEntity<Result> authUser(String key,String userId){
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).singleResult();
        repositoryService.addCandidateStarterUser(definition.getId(),userId);
        return doSuccess("授权成功");
    }

    /**
     * 指定用户组能启动(存act_ru_identitylink中间表)
     * @return
     */
    @GetMapping("/authGroup")
    public ResponseEntity<Result> authGroup(String key,String groupId){
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).singleResult();
        repositoryService.addCandidateStarterGroup(definition.getId(),groupId);
        return doSuccess("授权成功");
    }

    /**
     * 获取用户有权启动流程
     * @return
     */
    @GetMapping("/wfByUser")
    public ResponseEntity<Result> wfByUserId(String userId){
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().startableByUser(userId).list();
        return doSuccess(definitions);
    }

}
