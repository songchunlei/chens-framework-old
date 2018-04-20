package com.chens.bpm.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 我的申请/我发起的流程任务实体
 * @author WDP
 *
 */
public class MyStartProcessInstance implements Serializable {


	private static final long serialVersionUID = 1904470331012027160L;

	/**
	 * 流程实例id
	 */
	private String processInstanceId;

	/**
	 * 流程定义id
	 */
	private String processDefinitionId;

	/**
	 * 流程定义key
	 */
	private String processDefinitionKey;

	/**
	 * 流程定义名称
	 */
	private String processDefinitionName;

	/**
	 * 流程版本
	 */
	private int processDefinitionVersion;

	/**
	 * 任务名称
	 */
	private String taskName;

	/**
	 * 业务数据id
	 */
	private String businessKey;

	/**
	 * 流程状态
	 */
	private String status;

	/**
	 * 所属业务表
	 */
	private String tableName;

	/**
	 * 流程发起人id
	 */
	private String startBy;

	/**
	 * 流程发起人姓名
	 */
	private String startByName;

	/**
	 * 租户id
	 */
	private String tenantId;

	/**
	 * 任务发起时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 任务结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	/**
	 * 当前任务处理人
	 */
	private String currentAssigneeName;

	/**
	 * 任务节点key
	 */
	private String currentTaskDefinitionKey;

	/**
	 * 任务节点名称
	 */
	private String currentTaskDefinitionName;

	/**
	 * 花费时间
	 */
	private long duraton;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public int getProcessDefinitionVersion() {
		return processDefinitionVersion;
	}

	public void setProcessDefinitionVersion(int processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}



	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getCurrentAssigneeName() {
		return currentAssigneeName;
	}

	public void setCurrentAssigneeName(String currentAssigneeName) {
		this.currentAssigneeName = currentAssigneeName;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
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

	public long getDuraton() {
		return duraton;
	}

	public void setDuraton(long duraton) {
		this.duraton = duraton;
	}
	
}
