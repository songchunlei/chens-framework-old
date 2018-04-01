package com.chens.bpm.vo;

import java.io.Serializable;
import java.util.Map;
/**
 * 流程请求参数实体
 * @author WDP
 *
 */
public class WorkFlowRequestParam <T> implements Serializable{

	private static final long serialVersionUID = -7593268591473866547L;
	private String processDefinitionKey;//流程定义Key
    private String businessKey;//业务数据key,可以是ID，也可以是其他唯一性字段
    private Map<String, Object> variables;//流程变量
    private String taskId;//任务Id
    private String startUserId;//发起人id   
    private String startUserName;//发起人姓名
    private String bpmReason;//批示（内容描述）   
    private final String variableName = "bpm_advice";//变量名称 
    private String variableValue; //变量值
    //下一个环节处理人 若多个 用逗号分隔  ，目前 要么一个参与人  要么多个参与人（会签）暂不考虑 按角色抢单的模式 
    private String nextUserId;
    private String processInstanceName;//流程实例名称
    private String processDefinitionName;//流程定义名称
    private String processInstanceId;//流程实例id
    private String processDefinitionVersion;//流程定义版本
    private String processDefinitionId;//流程定义id
    private String taskName;//任务名称
    private String currentTaskDefinitionKey;//任务节点key
    private String currentTaskDefinitionName;//任务节点名称
    private String tableName;//业务表名 
    private T t;//业务实体
    private String tenantId;//租户id
    
    
    
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStartUserId() {
		return startUserId;
	}
	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}
	public String getBpmReason() {
		return bpmReason;
	}
	public void setBpmReason(String bpmReason) {
		this.bpmReason = bpmReason;
	}
	public String getVariableValue() {
		return variableValue;
	}
	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}
	public String getNextUserId() {
		return nextUserId;
	}
	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}
	public String getProcessInstanceName() {
		return processInstanceName;
	}
	public void setProcessInstanceName(String processInstanceName) {
		this.processInstanceName = processInstanceName;
	}
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
	public String getVariableName() {
		return variableName;
	}
	public String getStartUserName() {
		return startUserName;
	}
	public void setStartUserName(String startUserName) {
		this.startUserName = startUserName;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getProcessDefinitionVersion() {
		return processDefinitionVersion;
	}
	public void setProcessDefinitionVersion(String processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}
