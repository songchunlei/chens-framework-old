package com.chens.bpm.controller;

import java.util.HashMap;
import java.util.Map;

import com.chens.bpm.constants.BpmConstants;
import com.chens.bpm.exception.BpmException;
import com.chens.bpm.exception.BpmExceptionEnum;
import com.chens.bpm.vo.*;
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
     * 自定义初始化流程key
     * @return 流程key
     */
    protected abstract String getProcessDefinitionKey();

    /**
     * 自定义初始化任务名称
     * @param t
     * @return 返回任务名称
     */
    protected abstract String getProcessTaskName(T t);

    /**
     * 初始化发起参数
     * @param t
     */
    protected StartWfVo doStartInit(T t)
    {
        StartWfVo startWfVo = new StartWfVo();
        //流程key
        startWfVo.setProcessDefinitionKey(this.getProcessDefinitionKey());
        //实体
        startWfVo.setObject(t);
        //发起人
        startWfVo.setStartUserId(BaseContextHandler.getUserId());
        //发起人姓名
        startWfVo.setStartUserName(BaseContextHandler.getName());
        //租户
        startWfVo.setTenantId(BaseContextHandler.getTenantId());
        //流程任务名
        startWfVo.setTaskName(this.getProcessTaskName(t));
        //表名
        TableName tableName = t.getClass().getAnnotation(TableName.class);
        if(tableName!=null)
        {
            //从注解获取类名
            startWfVo.setTableName(tableName.value());
        }
        //意见
        startWfVo.setBpmReason(t.getBpmReason());
        return startWfVo;
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
            checkUser(t);
        	this.doStartInit(t);
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
            checkUser(t);
            return doSuccess("提交成功",service.submitDraft(this.doStartInit(t)));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 审批通过
     * @param passVo
     * @return
     */
    @PostMapping("/pass")
    public ResponseEntity<Result> pass(@RequestBody @Validated PassWfVo passVo) {
        if(passVo != null){
            //校验
            checkAssignee(passVo.getTaskId());
            //通过是直接结束，写死
            passVo.setNextUserId("admin");
            //现在只有一个审批节点通过就直接结束流程，直接写死，如果有多个节点，则这里需要从前台传过来
            passVo.setNextTaskKey("pass");
            //只读审批，关闭内容更新
            passVo.setObject(null);
            return doSuccess("办理成功",service.pass(passVo));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }


    /**
     * 可编辑提交审批  主要用于驳回至发起人修改后重新提交
     * @param passVo
     * @return
     */
    @PostMapping("/passWithEdit")
    public ResponseEntity<Result> passWithEdit(@RequestBody @Validated PassWfVo passVo) {
        if(passVo != null){
            //校验是否是当前处理人
            checkAssignee(passVo.getTaskId());
            //通过是直接结束，写死
            passVo.setNextUserId("admin");
            //现在只有一个审批节点通过就直接结束流程，直接写死，如果有多个节点，则这里需要从前台传过来
            passVo.setNextTaskKey("approveNode");
            return doSuccess(BpmConstants.PROCESS_TASK_DONE_SUCCESS,service.pass(passVo));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }


    /**
     * 审批不通过驳回
     * @param passVo
     * @return
     */
    @PostMapping("/noPass")
    public ResponseEntity<Result> noPass(@RequestBody @Validated PassWfVo passVo) {
        if(passVo != null){
            //校验是否是当前处理人
        	checkAssignee(passVo.getTaskId());
        	//现在只有一个审批环节， 审批不通过直接驳货至发起人节点， VariableValue统一传startNode,  发起人节点的用户Id去当前流程的发起人
        	String nextUserId = wfEngineService.getProcessStarterByTaskId(passVo.getTaskId());
            passVo.setNextUserId(nextUserId);
            passVo.setNextTaskKey("startNode");
        	if(service.noPass(passVo)){
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
    public ResponseEntity<Result> getApproveFormData(@RequestBody @Validated QueryWfFormVo queryFormVo) {
        if(queryFormVo != null){
        	Map<String, Object> map = new HashMap<String, Object>();
        	//获取流程信息
        	ProcessBussinessRel processBussinessRel = new ProcessBussinessRel();
        	processBussinessRel.setBusinessKey(queryFormVo.getId());
        	EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(processBussinessRel);
        	processBussinessRel = processBussinessRelService.selectOne(ew);
        	if(processBussinessRel != null && StringUtils.isNotEmpty(queryFormVo.getTaskId())){
        		processBussinessRel.setTaskId(queryFormVo.getTaskId());
        		Map<String, Object> taskInfoMap = wfEngineService.getTaskInfoByTaskId(queryFormVo.getTaskId());
        		processBussinessRel.setCurrentTaskDefinitionKey((String)taskInfoMap.get("taskDefinitionKey"));
        		processBussinessRel.setCurrentTaskDefinitionName((String)taskInfoMap.get("taskDefinitionName"));
        		map.put("processInfo", processBussinessRel);
        	}
        	else
            {
                map.put("processInfo", null);
            }
        	//获取表单详情
        	T t = service.selectById(queryFormVo.getId());
        	map.put("businessData", t);
            return doSuccess("查询成功",map);
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 校验是否是当前创建人
     * @param t
     * @return
     */
    public void checkUser(T t){
        boolean isCurrentCreator;
    	if(StringUtils.isNotBlank(t.getId())){
    		t = service.selectById(t.getId());
    		if(!StringUtils.equals(t.getCreateBy(), BaseContextHandler.getUserId())){
                isCurrentCreator = false;
    		}else{
                isCurrentCreator = true;
    		}
    	}else{
            isCurrentCreator = true;
    	}
    	if(!isCurrentCreator)
        {
            throw new BpmException(BpmExceptionEnum.ONLY_MODIFY_CREATED_DATA);
        }
    }

    /**
     * 校验是否是当前处理人
     * @param taskId
     * @return
     */
    public void checkAssignee(String taskId){
        boolean isCurrentAssignee;
        Map<String, Object> taskInfoMap = wfEngineService.getTaskInfoByTaskId(taskId);
        if(taskInfoMap == null){
            isCurrentAssignee = false;
        }else{
            if(StringUtils.equals((String)taskInfoMap.get("assignee"), BaseContextHandler.getUserId())){
                isCurrentAssignee =  true;
            }else{
                isCurrentAssignee =  false;
            }
        }
        if(!isCurrentAssignee)
        {
            throw new BpmException(BpmExceptionEnum.NOT_CURRENT_ASSIGNEE_CANNOT_SUBMIT);
        }
    }
    
    
   


}
