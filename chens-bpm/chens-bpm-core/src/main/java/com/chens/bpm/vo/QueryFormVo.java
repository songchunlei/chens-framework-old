package com.chens.bpm.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询表单信息
 *
 * @author songchunlei@qq.com
 * @create 2018/5/2
 */
public class QueryFormVo implements Serializable {
    /**
     * 业务id
     */
    @NotNull(message = "{data.id.null}")
    private String id;

    /**
     * 任务节点id
     */
    private String taskId;

    public QueryFormVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
