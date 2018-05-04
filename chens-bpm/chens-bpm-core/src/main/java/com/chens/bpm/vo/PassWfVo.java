package com.chens.bpm.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 提交流程
 * @author songchunlei@qq.com
 * @create 2018/5/3
 */
public class PassWfVo implements Serializable {

    /**
     * 业务id
     */
    protected String businessKey;

    /**
     * 当前任务id
     */
    protected String taskId;

    /**
     * 下一个处理人
     */
    protected String nextUserId;

    /**
     * 下一节点id
     */
    protected String nextTaskKey;

    /**
     * 业务实体
     */
    protected Object object;

    public PassWfVo() {
    }


    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
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
}
