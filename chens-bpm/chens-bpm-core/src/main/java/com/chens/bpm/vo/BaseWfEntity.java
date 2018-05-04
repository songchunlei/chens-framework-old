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
    protected String nextTaskKey;

    /**
     * 流程意见
     */
    @TableField(exist = false)
    protected String bpmReason;


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

    public String getNextTaskKey() {
        return nextTaskKey;
    }

    public void setNextTaskKey(String nextTaskKey) {
        this.nextTaskKey = nextTaskKey;
    }

    public String getBpmReason() {
        return bpmReason;
    }

    public void setBpmReason(String bpmReason) {
        this.bpmReason = bpmReason;
    }
}
