package com.chens.bpm.vo;

import com.chens.bpm.constants.BpmConstants;
import com.chens.core.constants.CommonConstants;

import java.io.Serializable;

/**
 * 发起流程(特殊的提交流程)
 * @author songchunlei@qq.com
 * @create 2018/5/3
 */
public class StartWfVo implements Serializable{


    /**
     * 流程key
     */
    private String processDefinitionKey;

    /**
     * 下一个处理人
     */
    private String nextUserId;

    /**
     * 下一节点id
     */
    private String nextTaskKey = BpmConstants.SIMPLE_PROCESS_TASK_KEY_APPROVE_NODE;

    /**
     * 业务实体
     */
    private Object object;

    /**
     * 发起人
     */
    private String startUserId;

    /**
     * 发起人姓名
     */
    private String StartUserName;

    /**
     * 发起人租户
     */
    private String tenantId;

    /**
     * 发起意见
     */
    private String bpmReason;

    /**
     * 发起业务表名
     */
    private String tableName;

    /**
     * 任务名称
     * @return
     */
    private String taskName;

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getNextUserId() {
        return nextUserId;
    }

    public void setNextUserId(String nextUserId) {
        this.nextUserId = nextUserId;
    }

    public String getNextTaskKey() {
        return nextTaskKey;
    }

    public void setNextTaskKey(String nextTaskKey) {
        this.nextTaskKey = nextTaskKey;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getStartUserName() {
        return StartUserName;
    }

    public void setStartUserName(String startUserName) {
        StartUserName = startUserName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBpmReason() {
        return bpmReason;
    }

    public void setBpmReason(String bpmReason) {
        this.bpmReason = bpmReason;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
