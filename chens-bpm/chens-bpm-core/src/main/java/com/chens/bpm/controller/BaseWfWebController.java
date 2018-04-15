package com.chens.bpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.bpm.service.IProcessBussinessRelService;
import com.chens.bpm.service.IWfBaseService;
import com.chens.bpm.service.IWfEngineService;
import com.chens.bpm.vo.BaseWfEntity;
import com.chens.bpm.vo.WorkFlowRequestParam;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;

/**
 * 流程抽象方法
 *
 * @auther songchunlei@qq.com
 * @create 2018/4/1
 */
public abstract class BaseWfWebController<S extends IWfBaseService<T>, T extends BaseWfEntity<T>>  extends BaseWebController<S,T> {


    protected WorkFlowRequestParam<T> workFlowRequestParam = new WorkFlowRequestParam<T>();
    
    @Autowired
    protected IWfEngineService wfEngineService;
    @Autowired
    protected IProcessBussinessRelService processBussinessRelService;

    /**
     * 自定义初始化
     */
    protected abstract void init(T t);


    /**
     * 初始化参数
     * @param t
     */
    protected void doInit(T t)
    {
    	init(t);
//        WorkFlowRequestParam<T> workFlowRequestParam = new WorkFlowRequestParam<T>();
        workFlowRequestParam.setProcessDefinitionKey(t.getProcessDefinitionKey());//流程定义Key
        workFlowRequestParam.setVariableValue(t.getVariableValue());//前台传过来的下一环节选择
        workFlowRequestParam.setTaskId(t.getTaskId());//任务id
        workFlowRequestParam.setNextUserId(t.getNextUserId());//下一处理人
        workFlowRequestParam.setStartUserId(BaseContextHandler.getUserId());//发起人
        workFlowRequestParam.setStartUserName(BaseContextHandler.getName());//发起人姓名
        workFlowRequestParam.setTenantId(BaseContextHandler.getTenantId());//租户
        //workFlowRequestParam.setTableName("t_source");//表名
        TableName tableName = t.getClass().getAnnotation(TableName.class);
        if(tableName!=null)
        {
            workFlowRequestParam.setTableName(tableName.value());//从注解获取类名
        }
        workFlowRequestParam.setBpmReason(t.getBpmReason());//审批意见
        workFlowRequestParam.setTaskName(t.getTaskName());
        workFlowRequestParam.setCurrentTaskDefinitionKey(t.getCurrentTaskDefinitionKey());
        workFlowRequestParam.setCurrentTaskDefinitionName(t.getCurrentTaskDefinitionName());
        workFlowRequestParam.setT(t);
        
    }

    /**
     * 创建草稿
     * @param t
     * @return
     */
    @Override
    @PostMapping("/createDraft")
    public ResponseEntity<Result> create(@RequestBody @Validated T t) {
        if(t != null){
        	this.doInit(t);
            return doSuccess("保存成功",service.createDraft(workFlowRequestParam));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 提交
     * @param t
     * @return
     */
    @PostMapping("/submitDraft")
    public ResponseEntity<Result> submitDraft(@RequestBody @Validated T t) {
        if(t != null){
            this.doInit(t);
            return doSuccess("提交成功",service.submitDraft(workFlowRequestParam));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 审批通过
     * @param t
     * @return
     */
    @PostMapping("/pass")
    public ResponseEntity<Result> pass(@RequestBody @Validated T t) {
        if(t != null){
        	this.doInit(t);
        	workFlowRequestParam.setNextUserId("admin");//通过是直接结束，写死
        	workFlowRequestParam.setVariableValue("pass");//现在只有一个审批节点通过就直接结束流程，直接写死，如果有多个节点，则这里需要从前台传过来
            return doSuccess("办理成功",service.pass(workFlowRequestParam));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }


    /**
     * 审批不通过驳回
     * @param t
     * @return
     */
    @PostMapping("/noPass")
    public ResponseEntity<Result> noPass(@RequestBody @Validated T t) {
        if(t != null){
        	this.doInit(t);
        	//现在只有一个审批环节， 审批不通过直接驳货至发起人节点， VariableValue统一传startNode,  发起人节点的用户Id去当前流程的发起人
        	String nextUserId = wfEngineService.getProcessStarterByTaskId(workFlowRequestParam.getTaskId());
        	workFlowRequestParam.setNextUserId(nextUserId);
        	workFlowRequestParam.setVariableValue("startNode");
        	if(service.noPass(workFlowRequestParam)){
        		return doSuccess("驳回成功");
        	}else{
        		return doError("驳回失败");
        	}
            
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 发布
     * @param t
     * @return
     */
    @PostMapping("/publish")
    public ResponseEntity<Result> publish(@RequestBody @Validated T t) {
        if(t != null){
            return doSuccess(service.publish(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 取消发布
     * @param t
     * @return
     */
    @PostMapping("/unPublish")
    public ResponseEntity<Result> unPublish(@RequestBody @Validated T t) {
        if(t != null){
            return doSuccess(service.unPublish(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    
    /**
     * 可编辑提交审批  主要用于驳回至发起人修改后重新提交
     * @param t
     * @return
     */
    @PostMapping("/passWithEdit")
    public ResponseEntity<Result> passWithEdit(@RequestBody @Validated T t) {
        if(t != null){
        	this.doInit(t);
        	workFlowRequestParam.setVariableValue("approveNode");//现在只有一个环节，所以发起人提交还是提交这个节点，暂时写死
            return doSuccess("办理成功",service.passWithEdit(workFlowRequestParam));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }


}
