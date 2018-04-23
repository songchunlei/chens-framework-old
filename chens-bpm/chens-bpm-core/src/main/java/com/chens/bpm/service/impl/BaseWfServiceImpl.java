package com.chens.bpm.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.bpm.exception.BpmException;
import com.chens.bpm.exception.BpmExceptionEnum;
import com.chens.bpm.vo.BaseWfEntity;
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
import com.chens.bpm.vo.WorkFlowRequestParam;
import com.chens.bpm.vo.WorkFlowReturn;
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
    	//T t = workFlowRequestParam.getT();
		//保存业务数据
		if(StringUtils.isEmpty(t.getId())){
			//默认状态为待提交
			t.setStatus(WfStatus.WAITING.getCode());
			//调用自定义保存方法
			saveEntity(t);
			//保存流程业务关联关系表
			ProcessBussinessRel processBussinessRel = new ProcessBussinessRel();
			//任务名称
			processBussinessRel.setTaskName(t.getTaskName());
			//草稿状态
			processBussinessRel.setStatus(WfStatus.WAITING.getCode());
			processBussinessRel.setProcessDefinitionKey(t.getProcessDefinitionKey());
			//业务数据id
			processBussinessRel.setBusinessKey(t.getId());
			//逻辑删除
			processBussinessRel.setIsDelete(YesNoEnum.NO.getCode());
			//发起人
			processBussinessRel.setStartBy(BaseContextHandler.getUserId());
			//发起人姓名
			processBussinessRel.setStartByName(BaseContextHandler.getName());
			//业务表名
			TableName tableName = t.getClass().getAnnotation(TableName.class);
			if(tableName!=null)
			{
				//从注解获取类名
				processBussinessRel.setTableName(tableName.value());
			}
			processBussinessRelService.insert(processBussinessRel);
		}else{
			String taskName = t.getTaskName();
			saveEntity(t);
			ProcessBussinessRel processBussinessRel = new ProcessBussinessRel();
			processBussinessRel.setBusinessKey(t.getId());
			EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(processBussinessRel);
			processBussinessRel = processBussinessRelService.selectOne(ew);
			if(processBussinessRel != null){
				processBussinessRel.setTaskName(taskName);
				processBussinessRelService.updateById(processBussinessRel);
			}
			
		}
		
        return t.getId();
    }

	/**
	 * 提交前方法
	 * 注意：如果要保持事务一致性，请throw BaseException()
	 * @param workFlowRequestParam
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public abstract boolean beforeSubmit(WorkFlowRequestParam<T> workFlowRequestParam);

	/**
	 * 提交后方法
	 * 注意：如果要保持事务一致性，请throw BaseException()
	 * @param workFlowRequestParam
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
    public abstract boolean afterSubmit(WorkFlowRequestParam<T> workFlowRequestParam);


    /**
     * 提交
     * @param workFlowRequestParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitDraft(WorkFlowRequestParam<T> workFlowRequestParam) {

		//获取业务数据
		T t = workFlowRequestParam.getT();
		if (t == null)
		{
			throw new BaseException(BaseExceptionEnum.DATA_REQUEST_ERROR);
		}

		//提交前方法
    	if(!beforeSubmit(workFlowRequestParam))
		{
			return false;
		}

		//提交状态变更为审批中
		t.setStatus(WfStatus.CHECKING.getCode());
		//调用保存业务实体
		saveEntity(t);

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
        		processBussinessRel.setStartBy(workFlowRequestParam.getStartUserId());
				//发起人姓名
        		processBussinessRel.setStartByName(workFlowRequestParam.getStartUserName());
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
        		processBussinessRel.setStartBy(workFlowRequestParam.getStartUserId());
				//发起人姓名
        		processBussinessRel.setStartByName(workFlowRequestParam.getStartUserName());
//        		processBussinessRel.setCurrentTaskDefinitionKey(currentTaskDefinitionKey);//当前任务节点key
//        		processBussinessRel.setCurrentTaskDefinitionName(currentTaskDefinitionName);//当前任务节点名称
				//任务名称
        		processBussinessRel.setTaskName(workFlowRequestParam.getTaskName());
				//流程定义key
    	        processBussinessRel.setProcessDefinitionKey(workFlowRequestParam.getProcessDefinitionKey());
				//业务数据id
    	        processBussinessRel.setBusinessKey(t.getId());
				//逻辑删除
    	        processBussinessRel.setIsDelete(YesNoEnum.NO.getCode());
				//业务表名
    	        processBussinessRel.setTableName(workFlowRequestParam.getTableName());
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
     * 审批通过（只读审批）
     * @param workFlowRequestParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
    			//更新业务
    			t.setStatus(WfStatus.PASS.getCode());
    			this.updateById(t);
    			return true;
    		}else{
    			//流程已结束，更新状态
    			this.updateProcessBussinessRelService(processBussinessRelList, WfStatus.CHECKING.getCode());
				//更新业务
				t.setStatus(WfStatus.CHECKING.getCode());
				this.updateById(t);
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
	@Transactional(rollbackFor = Exception.class)
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
     * @param workFlowRequestParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean noPass(WorkFlowRequestParam<T> workFlowRequestParam){
        T t = workFlowRequestParam.getT();
        workFlowRequestParam.setBusinessKey(t.getId());
    	WorkFlowReturn workFlowReturn = wfEngineService.completeTask(workFlowRequestParam);
    	if(workFlowReturn.isCompleteSuccess()){
    		ProcessBussinessRel query = new ProcessBussinessRel();
        	query.setBusinessKey(t.getId());
        	query.setIsDelete(YesNoEnum.NO.getCode());
        	EntityWrapper<ProcessBussinessRel> ew = new EntityWrapper<ProcessBussinessRel>(query);
        	List<ProcessBussinessRel> processBussinessRelList = processBussinessRelService.selectList(ew);
    		//流程已结束，更新状态为未通过
    		this.updateProcessBussinessRelService(processBussinessRelList, WfStatus.UN_PASS.getCode());
    		//更新业务表单
			t.setStatus(WfStatus.UN_PASS.getCode());
			this.updateById(t);
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
