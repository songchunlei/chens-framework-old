package com.chens.core.entity.workflow;

import java.io.Serializable;
import java.util.Map;
/**
 * 流程请求参数实体
 * @author WDP
 *
 */
public class WorkFlowRequestParam implements Serializable{

  
	private static final long serialVersionUID = 5735721405436804961L;
	private String processDefinitionKey;//流程定义Key
    private String businessKey;//业务数据key,可以是ID，也可以是其他唯一性字段
    private Map<String, Object> variables;//流程变量
    private String taskId;//任务Id
    private String startUserId;//发起人id   
    private String bpmReason;//批示（内容描述）   
    private final String variableName = "bpm_advice";//变量名称 
    private String variableValue; //变量值
    //下一个环节处理人 若多个 用逗号分隔  ，目前 要么一个参与人  要么多个参与人（会签）暂不考虑 按角色抢单的模式 
    private String nextUserId;
    private String processInstanceName;//流程实例名称
    private String processDefinitionName;//流程定义名称
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
    

}
