package com.chens.bpm.demo.controller;

import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户组测试demo(用GetMapping是为了测试省事，不要这么用)
 *
 * @author songchunlei@qq.com
 * @create 2018/1/3
 */
@Controller
@RequestMapping("/user")
public class UserController  extends BaseController {

    /**
     * 用户
     */
    @Autowired
    private IdentityService identityService;

    /**
     * 新建用户组
     * @return
     */
    @GetMapping("/newGroup")
    public ResponseEntity<Result> newGroup(){
        for (int i = 0; i < 10; i++) {
            Group group = identityService.newGroup(String.valueOf(i));
            group.setName("Group_"+i);
            group.setType("Type_"+i);
            identityService.saveGroup(group);
        }

        return doSuccess("保存成功");
    }

    /**
     * 查询用户组列表
     * @return
     */
    @GetMapping("/listGroup")
    public ResponseEntity<Result> listGroup(){
        //多字段排序
        List<Group> groupList =identityService.createGroupQuery().orderByGroupName().desc().orderByGroupId().asc().list();
        StringBuffer stringBuffer = new StringBuffer();
        for (Group group:groupList) {
            stringBuffer.append(group.getId() + "-" + group.getName() + "-" + group.getType() + ",");
        }
        return doSuccess(stringBuffer.toString());
    }

    /**
     * 多字段查询用户组列表
     * @return
     */
    @GetMapping("/mListGroup")
    public ResponseEntity<Result> mListGroup(){
        //多字段查询，and查询
        List<Group> groupList =identityService.createGroupQuery().groupName("Group_1").groupType("Type_2").list();
        StringBuffer stringBuffer = new StringBuffer();
        for (Group group:groupList) {
            stringBuffer.append(group.getId() + "-" + group.getName() + "-" + group.getType() + ",");
        }
        return doSuccess(groupList);
    }

    /**
     * 原生查询用户组列表
     * @return
     */
    @GetMapping("/nListGroup")
    public ResponseEntity<Result> nListGroup(){
        List<Group> groupList =identityService.createNativeGroupQuery().sql("SELECT * FROM ACT_ID_GROUP WHERE NAME_=#{name}").parameter("name","Group_2").list();
        StringBuffer stringBuffer = new StringBuffer();
        for (Group group:groupList) {
            stringBuffer.append(group.getId() + "-" + group.getName() + "-" + group.getType() + ",");
        }
        return doSuccess(groupList);
    }


    /**
     * 新建用户
     * @return
     */
    @GetMapping("/newUser")
    public ResponseEntity<Result> newUser(){
        for (int i = 0; i < 10; i++) {
            User user = identityService.newUser(String.valueOf(i));
            user.setLastName("Last_"+i);
            user.setFirstName("First_"+i);
            identityService.saveUser(user);
        }

        return doSuccess("保存成功");
    }


}
