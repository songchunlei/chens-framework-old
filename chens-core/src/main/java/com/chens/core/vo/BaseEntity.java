package com.chens.core.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.chens.core.constants.CommonConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础字段
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/12
 */


@SuppressWarnings("rawtypes")
public class BaseEntity <T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = -8484423417623201516L;

	@TableId(value = "id", type = IdType.UUID)
    protected String id;
    //private Long id; 不用private是为了让子类继承获取

    /**
     * 创建时间
     */
    @TableField(value = CommonConstants.BASE_COLUMN_CREATE_TIME,fill = FieldFill.INSERT)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = CommonConstants.BASE_COLUMN_UPDATE_TIME, fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;
    /**
     * 创建人
     */
    @TableField(value = CommonConstants.BASE_COLUMN_CREATE_BY, fill = FieldFill.INSERT)
    protected String createBy;
    /**
     * 更新人
     */
    @TableField(value = CommonConstants.BASE_COLUMN_UPDATE_BY, fill = FieldFill.INSERT_UPDATE)
    protected String updateBy;

    /**
     * 租户id
     */
    @TableField(value =CommonConstants.BASE_COLUMN_TENANT_ID)
    protected String tenantId;
    
    @TableField(exist = false)
    protected String taskId;//任务id
    
    @TableField(exist = false)
    protected String nextUserId;//下一环节处理人
    
    @TableField(exist = false)
    protected String variableValue;//下一节点选择
    
    @TableField(exist = false)
    protected String tableName;//表名
    
    @TableField(exist = false)
    protected String bpmReason;//流程意见
    
    @TableField(exist = false)
    protected String taskName;//任务名称
    
    @TableField(exist = false)
    protected String currentTaskDefinitionKey;//任务节点key
    
    @TableField(exist = false)
    protected String currentTaskDefinitionName;//任务节点名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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
	
	
}
