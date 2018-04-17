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
 * @author songchunlei@qq.com
 * @create 2018/3/12
 */
public class BaseEntity <T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = -8484423417623201516L;

	@TableId(value = "id", type = IdType.UUID)
    protected String id;

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
     * 创建人姓名
     */
    @TableField(value = CommonConstants.BASE_COLUMN_CREATE_BY_NAME, fill = FieldFill.INSERT)
    protected String createByName;

    /**
     * 更新人
     */
    @TableField(value = CommonConstants.BASE_COLUMN_UPDATE_BY, fill = FieldFill.INSERT_UPDATE)
    protected String updateBy;

    /**
     * 更新人
     */
    @TableField(value = CommonConstants.BASE_COLUMN_UPDATE_BY_NAME, fill = FieldFill.INSERT_UPDATE)
    protected String updateByName;

    /**
     * 租户id
     */
    @TableField(value =CommonConstants.BASE_COLUMN_TENANT_ID)
    protected String tenantId;

    /**
     * 多个id用英文逗号拼接，用于批量操作场景
     */
    @TableField(exist = false)
    private String idStr;
    


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

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

}
