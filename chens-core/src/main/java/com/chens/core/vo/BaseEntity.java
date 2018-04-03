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


	
}
