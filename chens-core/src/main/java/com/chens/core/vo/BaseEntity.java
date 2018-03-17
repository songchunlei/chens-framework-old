package com.chens.core.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础字段
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/12
 */
public class BaseEntity <T extends BaseEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    protected Long id;
    //private Long id; 不用private是为了让子类继承获取

    /**
     * 创建时间
     */
    @TableField("create_time")
    protected Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    protected Date updateTime;
    /**
     * 创建人
     */
    @TableField("create_by")
    protected Long createBy;
    /**
     * 更新人
     */
    @TableField("update_by")
    protected Long updateBy;
    /**
     * 租户id
     */
    @TableField("tenant_id")
    protected Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }


    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
