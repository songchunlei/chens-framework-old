package com.chens.bpm.controller;

import java.util.HashMap;
import java.util.Map;

import com.chens.bpm.vo.QueryFormVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.bpm.entity.ProcessBussinessRel;
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
 * @author songchunlei@qq.com
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
     * @param t
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
        //流程定义Key
        workFlowRequestParam.setProcessDefinitionKey(t.getProcessDefinitionKey());
        //前台传过来的下一环节选择
        workFlowRequestParam.setVariableValue(t.getVariableValue());
        //任务id
        workFlowRequestParam.setTaskId(t.getTaskId());
        //下一处理人
        workFlowRequestParam.setNextUserId(t.getNextUserId());
        //发起人
        workFlowRequestParam.setStartUserId(BaseContextHandler.getUserId());
        //发起人姓名
        workFlowRequestParam.setStartUserName(BaseContextHandler.getName());
        //租户
        workFlowRequestParam.setTenantId(BaseContextHandler.getTenantId());
        //workFlowRequestParam.setTableName("t_source");//表名
        TableName tableName = t.getClass().getAnnotation(TableName.class);
        if(tableName!=null)
        {
            //从注解获取类名
            workFlowRequestParam.setTableName(tableName.value());
        }
        //审批意见
        workFlowRequestParam.setBpmReason(t.getBpmReason());
//        workFlowRequestParam.setTaskName(t.getTaskName());
        workFlowRequestParam.setCurrentTaskDefinitionKey(t.getCurrentTaskDefinitionKey());
        workFlowRequestParam.setCurrentTaskDefinitionName(t.getCurrentTaskDefinitionName());
        workFlowRequestParam.setT(t);
    }

    /**
     * 保存
     * @param t
     * @return
     */
    @Override
    @PostMapping("/save")
    public ResponseEntity<Result> save(@RequestBody @Validated T t) {
        if(t != null){
        	//校验当前操作人是不是数据创建人
        	if(!checkUser(t)){
        		return doError("只能提交本人创建的数据！");
        	}        	
        	this.doInit(t);
            return doSuccess("保存成功",service.saveDraft(t));
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
        	//校验当前操作人是不是数据创建人
        	if(!checkUser(t)){
        		return doError("只能提交本人创建的数据！");
        	}
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
        	if(!checkAssignee(t)){
        		return doError("您非当前任务处理人员，无权审批！");
        	}      	
        	this.doInit(t);
            //通过是直接结束，写死
        	workFlowRequestParam.setNextUserId("admin");
            //现在只有一个审批节点通过就直接结束流程，直接写死，如果有多个节点，则这里需要从前台传过来
        	workFlowRequestParam.setVariableValue("pass");
            return doSuccess("办理成功",service.pass(workFlowRequestParam));
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
        	if(!checkAssignee(t)){
        		return doError("您非当前任务处理人员，无权审批！");
        	}
            this.doInit(t);
            //现在只有一个环节，所以发起人提交还是提交这个节点，暂时写死
            workFlowRequestParam.setVariableValue("approveNode");
            return doSuccess("办理成功",service.passWithEdit(workFlowRequestParam));
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
        	if(!checkAssignee(t)){
        		return doError("您非当前任务处理人员，无权审批！");
        	}
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
    @PutMapping("/publish")
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
    @PutMapping("/unPublish")
    public ResponseEntity<Result> unPublish(@RequestBody @Validated T t) {
        if(t != null){
            return doSuccess(service.unPublish(t));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
    
    
    /**
     * 获取审批表单
     * @param queryFormVo
     * @return
     */
    @PostMapping("/getApproveFormData")
    public ResponseEntity<Result> getApproveFormData(@RequestBody @Validated QueryFormVo queryFormVo) {
        if(queryFormVo != null){
        	Map<String, Object> map = new HashMap<String, Object>();
        	//获取流程信息
        	ProcessBussinessRel processBussinessRel = new ProcessBussinessRel();
        	processBussinessRel.setBusinessKey(queryFormVo.getId());
        	EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(processBussinessRel);
        	processBussinessRel = processBussinessRelService.selectOne(ew);
        	if(processBussinessRel != null){
        		processBussinessRel.setTaskId(queryFormVo.getTaskId());
        		Map<String, Object> taskInfoMap = wfEngineService.getTaskInfoByTaskId(queryFormVo.getTaskId());
        		processBussinessRel.setCurrentTaskDefinitionKey((String)taskInfoMap.get("taskDefinitionKey"));
        		processBussinessRel.setCurrentTaskDefinitionName((String)taskInfoMap.get("taskDefinitionName"));
        		map.put("processInfo", processBussinessRel);
        	}
        	//获取表单详情
        	T t = service.selectById(queryFormVo.getId());
        	map.put("businessData", t);
            return doSuccess("查询成功",map);
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
    
    public boolean checkUser(T t){
    	if(StringUtils.isNotBlank(t.getId())){
    		t = service.selectById(t.getId());
    		if(!StringUtils.equals(t.getCreateBy(), BaseContextHandler.getUserId())){
    			return false;
    		}else{
    			return true;
    		}
    	}else{
    		return true;
    	}
    }
    
    public boolean checkAssignee(T t){
    	 Map<String, Object> taskInfoMap = wfEngineService.getTaskInfoByTaskId(t.getTaskId());
    	 if(taskInfoMap == null){
    		 return false;
    	 }else{
    		 if(StringUtils.equals((String)taskInfoMap.get("assignee"), BaseContextHandler.getUserId())){
    			 return true;
    		 }else{
    			 return false;
    		 }
    	 }
    }
    
    
   


}
