package com.chens.bpm.service.impl;

import java.util.List;

import com.chens.bpm.constants.BpmConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.bpm.entity.ProcessBussinessRel;
import com.chens.bpm.enums.WfStatus;
import com.chens.bpm.service.IProcessBussinessRelService;
import com.chens.bpm.service.IWfBaseService;
import com.chens.bpm.service.IWfEngineService;
import com.chens.bpm.vo.WorkFlowRequestParam;
import com.chens.bpm.vo.WorkFlowReturn;
import com.chens.core.enums.YesNoEnum;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.BaseEntity;

/**
 * 流程引擎基础服务
 *
 * @auther wudepeng
 * @create 2018/3/30
 */
public abstract class BaseWfServiceImpl<M extends BaseMapper<T>, T extends BaseEntity<T>> extends ServiceImpl<M , T>  implements IWfBaseService<T>{

    @Autowired
    private IWfEngineService wfEngineService;
    @Autowired
    private IProcessBussinessRelService processBussinessRelService;

    /**
     * 创建草稿
     * @param workFlowRequestParam
     * @return
     */
    @Override
    @Transactional
    public String createDraft(WorkFlowRequestParam<T> workFlowRequestParam){
    	T t = workFlowRequestParam.getT();
        this.insert(t);//保存业务数据        
        ProcessBussinessRel processBussinessRel = new ProcessBussinessRel();//保存流程业务关联关系表
        processBussinessRel.setTaskName(workFlowRequestParam.getTaskName());//任务名称
        processBussinessRel.setStatus(WfStatus.WAITING.getCode());//草稿状态
        processBussinessRel.setProcessDefinitionKey(workFlowRequestParam.getProcessDefinitionKey());
        processBussinessRel.setBusinessKey(t.getId());//业务数据id
        processBussinessRel.setIsDelete(YesNoEnum.NO.getCode());//逻辑删除
        processBussinessRel.setTableName(workFlowRequestParam.getTableName());//业务表名
        processBussinessRelService.insert(processBussinessRel);               
        return t.getId();
    }

	/**
	 * 提交前方法
	 * 注意：如果要保持事务一致性，请throw BaseException()
	 * @param workFlowRequestParam
	 * @return
	 */
	@Transactional
	public abstract boolean beforeSubmit(WorkFlowRequestParam<T> workFlowRequestParam);

	/**
	 * 提交后方法
	 * 注意：如果要保持事务一致性，请throw BaseException()
	 * @param workFlowRequestParam
	 * @return
	 */
	@Transactional
    public abstract boolean afterSubmit(WorkFlowRequestParam<T> workFlowRequestParam);


    /**
     * 提交
     * @param workFlowRequestParam
     * @return
     */
    @Override
    @Transactional
    public boolean submitDraft(WorkFlowRequestParam<T> workFlowRequestParam) {
    	T t = workFlowRequestParam.getT();
    	/*
    	if(StringUtils.isNotBlank(t.getId())){
    		this.updateById(t);
    	}else{
    		this.insert(t);
    	}
    	*/

		//提交前方法
    	if(!beforeSubmit(workFlowRequestParam))
		{
			return false;
		}

        workFlowRequestParam.setBusinessKey(t.getId());
        WorkFlowReturn workFlowReturn = wfEngineService.startWorkflow(workFlowRequestParam);
        if(!workFlowReturn.isStartSuccess()){
        	throw new BaseException(BaseExceptionEnum.WORKFLOW_START_FAIL);
        }else{
        	JSONObject obj = workFlowReturn.getData();
        	ProcessBussinessRel query = new ProcessBussinessRel();
        	query.setBusinessKey(t.getId());
        	query.setIsDelete(YesNoEnum.NO.getCode());
        	EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(query);
        	List<ProcessBussinessRel> processBussinessRelList = processBussinessRelService.selectList(ew);
        	if(CollectionUtils.isNotEmpty(processBussinessRelList)){
        		ProcessBussinessRel processBussinessRel = processBussinessRelList.get(0);
        		processBussinessRel.setStatus(WfStatus.CHECKING.getCode());//审批中
        		processBussinessRel.setProcessDefinitionId(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_ID));//流程定义id
        		processBussinessRel.setProcessDefinitionName(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_NAME));//流程定义名称
        		processBussinessRel.setProcessDefinitionVersion(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_VERSION));//流程定义版本
        		processBussinessRel.setProcessInstanceId(obj.getString(BpmConstants.KEY_PROCESS_INSTANCE_ID));//流程实例id
        		processBussinessRel.setStartBy(workFlowRequestParam.getStartUserId());//发起人id
        		processBussinessRel.setStartByName(workFlowRequestParam.getStartUserName());//发起人姓名
//        		processBussinessRel.setCurrentTaskDefinitionKey(currentTaskDefinitionKey);//当前任务节点key
//        		processBussinessRel.setCurrentTaskDefinitionName(currentTaskDefinitionName);//当前任务节点名称
        		processBussinessRelService.updateById(processBussinessRel);
        	}else{
        		ProcessBussinessRel processBussinessRel = new ProcessBussinessRel();
        		processBussinessRel.setStatus(WfStatus.CHECKING.getCode());//审批中
        		processBussinessRel.setProcessDefinitionId(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_ID));//流程定义id
        		processBussinessRel.setProcessDefinitionName(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_NAME));//流程定义名称
        		processBussinessRel.setProcessDefinitionVersion(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_VERSION));//流程定义版本
        		processBussinessRel.setProcessInstanceId(obj.getString(BpmConstants.KEY_PROCESS_INSTANCE_ID));//流程实例id
        		processBussinessRel.setStartBy(workFlowRequestParam.getStartUserId());//发起人id
        		processBussinessRel.setStartByName(workFlowRequestParam.getStartUserName());//发起人姓名
//        		processBussinessRel.setCurrentTaskDefinitionKey(currentTaskDefinitionKey);//当前任务节点key
//        		processBussinessRel.setCurrentTaskDefinitionName(currentTaskDefinitionName);//当前任务节点名称        		
        		processBussinessRel.setTaskName(workFlowRequestParam.getTaskName());//任务名称
    	        processBussinessRel.setProcessDefinitionKey(workFlowRequestParam.getProcessDefinitionKey());//流程定义key
    	        processBussinessRel.setBusinessKey(t.getId());//业务数据id
    	        processBussinessRel.setIsDelete(YesNoEnum.NO.getCode());//逻辑删除
    	        processBussinessRel.setTableName(workFlowRequestParam.getTableName());//业务表名
    	        processBussinessRelService.insert(processBussinessRel);
        	}

			//提交后方法
			if(!afterSubmit(workFlowRequestParam))
			{
				return false;
			}

        	return true;
        }
        
    }

    /**
     * 提交办理（只读审批）
     * @param workFlowRequestParam
     * @return
     */
    @Override
    @Transactional
    public boolean pass(WorkFlowRequestParam<T> workFlowRequestParam)
    {
        T t = workFlowRequestParam.getT();
        workFlowRequestParam.setBusinessKey(t.getId());
    	WorkFlowReturn workFlowReturn = wfEngineService.completeTask(workFlowRequestParam);
    	if(workFlowReturn.isCompleteSuccess()){
    		ProcessBussinessRel query = new ProcessBussinessRel();
        	query.setBusinessKey(t.getId());
        	query.setIsDelete(YesNoEnum.NO.getCode());
        	EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(query);
        	List<ProcessBussinessRel> processBussinessRelList = processBussinessRelService.selectList(ew);
    		if(workFlowReturn.isFinish()){
    			//流程已结束，更新状态
    			this.updateProcessBussinessRelService(processBussinessRelList, WfStatus.PASS.getCode());
    			return true;
    		}else{
    			//流程已结束，更新状态
    			this.updateProcessBussinessRelService(processBussinessRelList, WfStatus.CHECKING.getCode());    			
    			return true;
    		}
    	}else{
    		return false;
    	}
    }

	/**
	 * 提交办理（非只读审批）
	 * @param workFlowRequestParam
	 * @return
	 */
	@Override
	@Transactional
	public boolean passWithEdit(WorkFlowRequestParam<T> workFlowRequestParam)
	{
		//提交前方法
		if(!beforeSubmit(workFlowRequestParam))
		{
			return false;
		}

		//流程方法
		if(!pass(workFlowRequestParam))
		{
			return false;
		}

		//提交后方法
		if(!afterSubmit(workFlowRequestParam))
		{
			return false;
		}

		return true;
	}
    
    public void updateProcessBussinessRelService(List<ProcessBussinessRel> processBussinessRelList, String status){
    	if(CollectionUtils.isNotEmpty(processBussinessRelList)){
			ProcessBussinessRel processBussinessRel = processBussinessRelList.get(0);
			processBussinessRel.setStatus(status);
			processBussinessRelService.updateById(processBussinessRel);
		}
    }

    /**
     * 审批不通过
     * @param t
     * @return
     */
    @Override
    @Transactional
    public boolean noPass(T t)
    {
        wfEngineService.unPassTask();
        return this.updateById(t);
    }

    @Override
    public boolean publish(T t) {
        return false;
    }

    @Override
    public boolean unPublish(T t) {
        return false;
    }



}
