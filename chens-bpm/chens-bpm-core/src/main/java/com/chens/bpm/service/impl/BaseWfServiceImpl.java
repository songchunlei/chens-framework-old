package com.chens.bpm.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.bpm.exception.BpmException;
import com.chens.bpm.exception.BpmExceptionEnum;
import com.chens.bpm.vo.*;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.bpm.constants.BpmConstants;
import com.chens.bpm.entity.ProcessBussinessRel;
import com.chens.bpm.enums.WfStatus;
import com.chens.bpm.service.IProcessBussinessRelService;
import com.chens.bpm.service.IWfBaseService;
import com.chens.bpm.service.IWfEngineService;
import com.chens.core.enums.YesNoEnum;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;

/**
 * 流程引擎基础服务
 *
 * @author wudepeng
 * @create 2018/3/30
 */
public abstract class BaseWfServiceImpl<M extends BaseMapper<T>, T extends BaseWfEntity<T>> extends ServiceImpl<M , T>  implements IWfBaseService<T>{

    @Autowired
    private IWfEngineService wfEngineService;
    @Autowired
    private IProcessBussinessRelService processBussinessRelService;

	/**
	 * 保存
	 * 注意：如果要保持事务一致性，请throw BaseException()
	 * @param t
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public abstract T saveEntity(T t);

    /**
     * 创建草稿
     * @param t
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveDraft(T t){

    	//设置状态-待提交
    	t.setStatus(WfStatus.WAITING.getCode());

    	//调用自定义保存业务实体
    	this.saveEntity(t);
		
        return t.getId();
    }


    /**
     * 提交
     * @param startWfVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitDraft(StartWfVo startWfVo) {

		//获取业务数据
		T t = (T)startWfVo.getObject();
		if (t == null)
		{
			throw new BaseException(BaseExceptionEnum.DATA_REQUEST_ERROR);
		}
		//提交状态变更为审批中
		t.setStatus(WfStatus.CHECKING.getCode());
		//调用保存业务实体
		this.saveEntity(t);

		//调用流程引擎-发起
        WorkFlowReturn workFlowReturn = wfEngineService.startWorkflow(t.getId(),startWfVo);

        if(!workFlowReturn.isStartSuccess()){
        	throw new BpmException(BpmExceptionEnum.WORKFLOW_START_FAIL);
        }else{
        	JSONObject obj = workFlowReturn.getData();
        	ProcessBussinessRel query = new ProcessBussinessRel();
        	query.setBusinessKey(t.getId());
        	query.setIsDelete(YesNoEnum.NO.getCode());
        	EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(query);
        	List<ProcessBussinessRel> processBussinessRelList = processBussinessRelService.selectList(ew);
        	if(CollectionUtils.isNotEmpty(processBussinessRelList)){
        		ProcessBussinessRel processBussinessRel = processBussinessRelList.get(0);
				//审批中
        		processBussinessRel.setStatus(WfStatus.CHECKING.getCode());
				//流程定义id
        		processBussinessRel.setProcessDefinitionId(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_ID));
				//流程定义名称
        		processBussinessRel.setProcessDefinitionName(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_NAME));
				//流程定义版本
        		processBussinessRel.setProcessDefinitionVersion(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_VERSION));
				//流程实例id
        		processBussinessRel.setProcessInstanceId(obj.getString(BpmConstants.KEY_PROCESS_INSTANCE_ID));
				//发起人id
        		processBussinessRel.setStartBy(startWfVo.getStartUserId());
				//发起人姓名
        		processBussinessRel.setStartByName(startWfVo.getStartUserName());
//        		processBussinessRel.setCurrentTaskDefinitionKey(currentTaskDefinitionKey);//当前任务节点key
//        		processBussinessRel.setCurrentTaskDefinitionName(currentTaskDefinitionName);//当前任务节点名称
        		processBussinessRelService.updateById(processBussinessRel);
        	}else{
        		ProcessBussinessRel processBussinessRel = new ProcessBussinessRel();
				//审批中
        		processBussinessRel.setStatus(WfStatus.CHECKING.getCode());
				//流程定义id
        		processBussinessRel.setProcessDefinitionId(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_ID));
				//流程定义名称
        		processBussinessRel.setProcessDefinitionName(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_NAME));
				//流程定义版本
        		processBussinessRel.setProcessDefinitionVersion(obj.getString(BpmConstants.KEY_PROCESS_DEFINITION_VERSION));
				//流程实例id
        		processBussinessRel.setProcessInstanceId(obj.getString(BpmConstants.KEY_PROCESS_INSTANCE_ID));
				//发起人id
        		processBussinessRel.setStartBy(startWfVo.getStartUserId());
				//发起人姓名
        		processBussinessRel.setStartByName(startWfVo.getStartUserName());
//        		processBussinessRel.setCurrentTaskDefinitionKey(currentTaskDefinitionKey);//当前任务节点key
//        		processBussinessRel.setCurrentTaskDefinitionName(currentTaskDefinitionName);//当前任务节点名称
				//任务名称
        		processBussinessRel.setTaskName(BpmConstants.PROCESS_START_TASK_NAME);
				//流程定义key
    	        processBussinessRel.setProcessDefinitionKey(startWfVo.getProcessDefinitionKey());
				//业务数据id
    	        processBussinessRel.setBusinessKey(t.getId());
				//逻辑删除
    	        processBussinessRel.setIsDelete(YesNoEnum.NO.getCode());
				//业务表名
    	        processBussinessRel.setTableName(startWfVo.getTableName());
    	        processBussinessRelService.insert(processBussinessRel);
        	}
        	return true;
        }
        
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean pass(PassWfVo passWfVo)
    {
    	//调用流程引擎-提交
    	WorkFlowReturn workFlowReturn = wfEngineService.completeTask(passWfVo);

    	//业务id
    	String businessKey = passWfVo.getBusinessKey();

    	//获取业务实例
		T change;
		if(passWfVo.getObject()!=null)
		{
			change = (T)passWfVo.getObject();
		}
		else {
			change = this.selectById(businessKey);
		}
    	if(change==null)
		{
			throw new BaseException(BaseExceptionEnum.NO_DATA);
		}

		//待更新状态
		String changeStatus;

    	if(workFlowReturn.isCompleteSuccess()){
    		ProcessBussinessRel query = new ProcessBussinessRel();
        	query.setBusinessKey(businessKey);
        	query.setIsDelete(YesNoEnum.NO.getCode());
        	EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(query);
        	List<ProcessBussinessRel> processBussinessRelList = processBussinessRelService.selectList(ew);
    		if(workFlowReturn.isFinish()){
    			//流程已结束，更新状态
    			this.updateProcessBussinessRelService(processBussinessRelList, WfStatus.PASS.getCode());
    			//变更状态
				changeStatus = WfStatus.PASS.getCode();
    		}else{
    			//流程已结束，更新状态
    			this.updateProcessBussinessRelService(processBussinessRelList, WfStatus.CHECKING.getCode());
				//变更状态
				changeStatus = WfStatus.CHECKING.getCode();
    		}
    	}else{
    		throw new BpmException(BpmExceptionEnum.COMPLETE_TASK_ERROR.getCode(), workFlowReturn.getMessage());
    	}

    	//保存信息
		change.setStatus(changeStatus);
		this.saveEntity(change);

		return true;

    }

	/**
	 * 更新流程关联关系
	 * @param processBussinessRelList
	 * @param status
	 */
    public void updateProcessBussinessRelService(List<ProcessBussinessRel> processBussinessRelList, String status){
    	if(CollectionUtils.isNotEmpty(processBussinessRelList)){
			ProcessBussinessRel processBussinessRel = processBussinessRelList.get(0);

			//状态是审批中/审批不通过，才能审批
			boolean isCanPass = (WfStatus.CHECKING.getCode().equals(processBussinessRel.getStatus()) || WfStatus.UN_PASS.getCode().equals(processBussinessRel.getStatus()))
					&& (WfStatus.CHECKING.getCode().equals(status) || WfStatus.UN_PASS.getCode().equals(status) || WfStatus.PASS.getCode().equals(status));

			if(!isCanPass)
			{
				throw new BpmException(BpmExceptionEnum.CAN_NOT_PASS);
			}

			processBussinessRel.setStatus(status);
			processBussinessRelService.updateById(processBussinessRel);

		}
    }

    /**
     * 审批不通过
     * @param passWfVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean noPass(PassWfVo passWfVo){

		//调用流程引擎-提交
		WorkFlowReturn workFlowReturn = wfEngineService.completeTask(passWfVo);

		//业务id
		String businessKey = passWfVo.getBusinessKey();

		//获取业务实例
		T change;
		if(passWfVo.getObject()!=null)
		{
			change = (T)passWfVo.getObject();
		}
		else {
			change = this.selectById(businessKey);
		}
		if(change==null)
		{
			throw new BaseException(BaseExceptionEnum.NO_DATA);
		}

    	if(workFlowReturn.isCompleteSuccess()){
    		ProcessBussinessRel query = new ProcessBussinessRel();
        	query.setBusinessKey(passWfVo.getBusinessKey());
        	query.setIsDelete(YesNoEnum.NO.getCode());
        	EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(query);
        	List<ProcessBussinessRel> processBussinessRelList = processBussinessRelService.selectList(ew);
    		//流程已结束，更新状态为未通过
    		this.updateProcessBussinessRelService(processBussinessRelList, WfStatus.UN_PASS.getCode());

    		//更新业务表单
			change.setStatus(WfStatus.UN_PASS.getCode());

			this.updateById(change);
    		return true;
    	}else{
    		return false;
    	}
    }

    @Override
    public boolean publish(T t) {
		if(StringUtils.isNotEmpty(t.getId()))
		{
			throw new BaseException(BaseExceptionEnum.DATA_REQUEST_ERROR);
		}
    	t = this.selectById(t.getId());
		if(t==null)
		{
			throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
		}
		//未审批通过，不能发布
		if(!WfStatus.PASS.getCode().equals(t.getStatus()))
		{
			throw new BpmException(BpmExceptionEnum.STATUS_IS_NOT_PASS);
		}
    	t.setStatus(WfStatus.PUBLISHED.getCode());
        return this.updateById(t);
    }

    @Override
    public boolean unPublish(T t) {
		if(StringUtils.isNotEmpty(t.getId()))
		{
			throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
		}
		t = this.selectById(t.getId());
		if(t==null)
		{
			throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
		}
		//状态不是已发布，不能被取消
		if(!WfStatus.PUBLISHED.getCode().equals(t.getStatus()))
		{
			throw new BpmException(BpmExceptionEnum.STATUS_IS_NOT_PUBLISH);
		}
		t.setStatus(WfStatus.CANCEL_PUBLISHED.getCode());
        return this.updateById(t);
    }



}
