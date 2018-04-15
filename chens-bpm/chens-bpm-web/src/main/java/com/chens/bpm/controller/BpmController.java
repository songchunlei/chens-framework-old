package com.chens.bpm.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.bpm.service.IWfEngineService;
import com.chens.bpm.vo.MyDoneTask;
import com.chens.bpm.vo.MyStartProcessInstance;
import com.chens.bpm.vo.MyTodoTask;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.vo.PageVo;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程服务页
 *
 * @author songchunlei@qq.com
 * @create 2018/4/15
 */
@Controller
@RequestMapping("/bpmController")
public class BpmController extends BaseController{
    @Autowired
    protected IWfEngineService wfEngineService;

    /**
     * 我的待办
     * @param spage
     * @return
     */
    @PostMapping("/getMyTodoTaskPage")
    public ResponseEntity<Result> getMyTodTaskPage(@RequestBody QueryPageEntity<MyTodoTask> spage){
        PageVo pageVo = spage.getPage();
        Page<MyTodoTask> page = this.createPage(spage);
        MyTodoTask myTodoTask = spage.getSearch();
        myTodoTask.setAssignee(BaseContextHandler.getUserId());
        return doSuccess(CommonConstants.QUERY_SUCCESS,wfEngineService.getMyTodoTaskPage(page, myTodoTask));
    }

    /**
     * 我的已办
     * @param spage
     * @return
     */
    @PostMapping("/getMyDoneTaskPage")
    public ResponseEntity<Result> getMyDoneTaskPage(@RequestBody QueryPageEntity<MyDoneTask> spage){
        PageVo pageVo = spage.getPage();
        Page<MyDoneTask> page = new Page<MyDoneTask>(pageVo.getCurrent(), pageVo.getSize());
        MyDoneTask myDoneTask = spage.getSearch();
        myDoneTask.setAssignee(BaseContextHandler.getUserId());
        return doSuccess(CommonConstants.QUERY_SUCCESS,wfEngineService.getMyDoneTaskPage(page, myDoneTask));
    }

    /**
     * 我发起的流程
     * @param spage
     * @return
     */
    @PostMapping("/getMyStartProcessInstancePage")
    public ResponseEntity<Result> getMyStartProcessInstancePage(@RequestBody QueryPageEntity<MyStartProcessInstance> spage){
        PageVo pageVo = spage.getPage();
        Page<MyStartProcessInstance> page = new Page<MyStartProcessInstance>(pageVo.getCurrent(), pageVo.getSize());
        MyStartProcessInstance myStartProcessInstance = spage.getSearch();
        myStartProcessInstance.setStartBy(BaseContextHandler.getUserId());
        return doSuccess(CommonConstants.QUERY_SUCCESS,wfEngineService.getMyStartProcessInstancePage(page, myStartProcessInstance));
    }

}
