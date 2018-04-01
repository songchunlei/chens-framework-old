package com.chens.bpm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 *
 *  实体
 *
 * @author wudepeng
 * @create 2018-04-01
 */
@TableName("t_process_bussiness_rel")
public class ProcessBussinessRel extends BaseEntity<ProcessBussinessRel> {

	private static final long serialVersionUID = -158968710878395018L;
	/**
     * 任务名称
     */
	@TableField("task_name")
	private String taskName;
    /**
     * 状态
     */
	private String status;
    /**
     * 流程定义id
     */
	@TableField("process_definition_id")
	private String processDefinitionId;
    /**
     * 流程定义Key
     */
	@TableField("process_definition_key")
	private String processDefinitionKey;
    /**
     * 流程名称
     */
	@TableField("process_definition_name")
	private String processDefinitionName;
	
	/**
	 * 流程定义版本
	 */
	@TableField("process_definition_version")
	private String processDefinitionVersion;
    /**
     * 流程实例id
     */
	@TableField("process_instance_id")
	private String processInstanceId;
    /**
     * 业务数据id
     */
	@TableField("business_key")
	private String businessKey;
    /**
     * 业务数据表名
     */
	@TableField("table_name")
	private String tableName;
    /**
     * 发起人id
     */
	@TableField("start_by")
	private String startBy;
    /**
     * 发起人姓名
     */
	@TableField("start_by_name")
	private String startByName;
    /**
     * 逻辑删除
     */
	@TableField("is_delete")
	private String isDelete;
    /**
     * 当前任务节点Key
     */
	@TableField("current_task_definition_key")
	private String currentTaskDefinitionKey;
    /**
     * 当前任务节点名称
     */
	@TableField("current_task_definition_name")
	private String currentTaskDefinitionName;

	

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getProcessDefinitionVersion() {
		return processDefinitionVersion;
	}

	public void setProcessDefinitionVersion(String processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getStartBy() {
		return startBy;
	}

	public void setStartBy(String startBy) {
		this.startBy = startBy;
	}

	public String getStartByName() {
		return startByName;
	}

	public void setStartByName(String startByName) {
		this.startByName = startByName;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getCurrentTaskDefinitionKey() {
		return currentTaskDefinitionKey;
	}

	public void setCurrentTaskDefinitionKey(String currentTaskDefinitionKey) {
		this.currentTaskDefinitionKey = currentTaskDefinitionKey;
	}

	public String getCurrentTaskDefinitionName() {
		return currentTaskDefinitionName;
	}

	public void setCurrentTaskDefinitionName(String currentTaskDefinitionName) {
		this.currentTaskDefinitionName = currentTaskDefinitionName;
	}

}
