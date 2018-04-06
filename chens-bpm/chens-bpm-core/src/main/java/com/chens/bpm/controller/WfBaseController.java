package com.chens.bpm.controller;

import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.bpm.vo.WfBaseEntity;
import com.chens.core.context.BaseContextHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chens.bpm.service.IWfBaseService;
import com.chens.bpm.vo.WorkFlowRequestParam;
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
public abstract class WfBaseController<S extends IWfBaseService<T>, T extends WfBaseEntity<T>>  extends BaseWebController<S,T> {


    protected WorkFlowRequestParam<T> workFlowRequestParam = new WorkFlowRequestParam<T>();

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
            return doSuccess("办理成功",service.pass(workFlowRequestParam));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }


    /**
     * 审批不通过
     * @param t
     * @return
     */
    @PostMapping("/noPass")
    public ResponseEntity<Result> noPass(@RequestBody @Validated T t) {
        if(t != null){
            return doSuccess(service.noPass(t));
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


}
