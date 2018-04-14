package com.chens.bpm.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 我的已办任务实体
 * @author WDP
 *
 */
public class MyDoneTask implements Serializable {


	private static final long serialVersionUID = 1904470331012027160L;
	
	private String taskId;//任务id
	
	private String taskDefinitionKey;//任务节点key
	
	private String taskDefinitionName;//任务节点名称
	
	private String processInstanceId;//流程实例id
	
	private String processDefinitionId;//流程定义id
	
	private String processDefinitionKey;//流程定义key
	
	private String processDefinitionName;//流程定义名称
	
	private int processDefinitionVersion;//流程版本
	
	private String assignee;//任务处理人
	
	private String taskName;//任务名称
	
	private String businessKey;//业务数据id
	
	private String status;//流程状态
	
	private String tableName;//所属业务表
	
	private String startBy;//流程发起人id
	
	private String startByName;//流程发起人姓名

	private String tenantId;//租户id
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;//任务接收时间 
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;//任务完成时间 
	
	private String currentAssigneeName;//当前任务处理人
	
	private String currentTaskDefinitionKey;//任务节点key
	
	private String currentTaskDefinitionName;//任务节点名称

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getTaskDefinitionName() {
		return taskDefinitionName;
	}

	public void setTaskDefinitionName(String taskDefinitionName) {
		this.taskDefinitionName = taskDefinitionName;
	}

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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
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
	
	
	
}
