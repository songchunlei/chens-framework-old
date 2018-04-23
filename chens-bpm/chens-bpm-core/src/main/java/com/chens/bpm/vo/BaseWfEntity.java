package com.chens.bpm.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.chens.core.vo.BaseEntity;

/**
 * 流程引擎基本虚拟字段
 *
 * @author songchunlei@qq.com
 * @create 2018/4/3
 */
public class BaseWfEntity<T extends BaseWfEntity> extends BaseEntity<T>{

	private static final long serialVersionUID = -6893793486319301365L;

    /**
     * 状态
     */
	protected String status;

    /**
     * 任务id
     */
	@TableField(exist = false)
    protected String taskId;

    /**
     * 下一环节处理人
     */
    @TableField(exist = false)
    protected String nextUserId;

    /**
     * 下一节点选择
     */
    @TableField(exist = false)
    protected String variableValue;

    /**
     * 表名
     */
    @TableField(exist = false)
    protected String tableName;

    /**
     * 流程意见
     */
    @TableField(exist = false)
    protected String bpmReason;

    /**
     * 任务名称
     */
    @TableField(exist = false)
    protected String taskName;

    /**
     * 任务节点key
     */
    @TableField(exist = false)
    protected String currentTaskDefinitionKey;

    /**
     * 任务节点名称
     */
    @TableField(exist = false)
    protected String currentTaskDefinitionName;

    /**
     * 流程key
     */
    @TableField(exist = false)
    protected String processDefinitionKey;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNextUserId() {
        return nextUserId;
    }

    public void setNextUserId(String nextUserId) {
        this.nextUserId = nextUserId;
    }

    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBpmReason() {
        return bpmReason;
    }

    public void setBpmReason(String bpmReason) {
        this.bpmReason = bpmReason;
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


    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    /*
    public WorkFlowRequestParam<T> getWorkFlowRequestParam()
    {
        WorkFlowRequestParam workFlowRequestParam = new WorkFlowRequestParam<T>();
        workFlowRequestParam.setProcessDefinitionKey(this.getProcessDefinitionKey());//流程定义Key
        workFlowRequestParam.setVariableValue(this.getVariableValue());//前台传过来的下一环节选择
        workFlowRequestParam.setTaskId(this.getTaskId());//任务id
        workFlowRequestParam.setNextUserId(this.getNextUserId());//下一处理人
        workFlowRequestParam.setStartUserId(BaseContextHandler.getUserId());//发起人
        workFlowRequestParam.setStartUserName(BaseContextHandler.getName());//发起人姓名
        workFlowRequestParam.setTenantId(BaseContextHandler.getTenantId());//租户
        //workFlowRequestParam.setTableName("t_source");//表名
        TableName tableName = this.getClass().getAnnotation(TableName.class);
        if(tableName!=null)
        {
            workFlowRequestParam.setTableName(tableName.value());//从注解获取类名
        }
        workFlowRequestParam.setBpmReason(this.getBpmReason());//审批意见
        workFlowRequestParam.setTaskName(this.getTaskName());
        workFlowRequestParam.setCurrentTaskDefinitionKey(this.getCurrentTaskDefinitionKey());
        workFlowRequestParam.setCurrentTaskDefinitionName(this.getCurrentTaskDefinitionName());
        workFlowRequestParam.setT(this);
        return workFlowRequestParam;
    }
    */
}
