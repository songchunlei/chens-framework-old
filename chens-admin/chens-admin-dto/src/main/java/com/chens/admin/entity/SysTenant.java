package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.vo.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@TableName("sys_tenant")
public class SysTenant extends BaseEntity<SysTenant> {

    private static final long serialVersionUID = 1L;


    /**
     * 租户名称
     */
    @NotNull(message = "{tenant.name.null}",groups = {InsertValid.class, UpdateValid.class})
    @TableField("tenant_name")
    private String tenantName;
    /**
     * 租户编码
     */
    @TableField("tenant_code")
    private String tenantCode;

    /**
     * 营业执照
     */
    @NotNull(message = "{tenant.jregLicens.null}",groups = {InsertValid.class, UpdateValid.class})
    @TableField("jreg_licens")
    private String jregLicens;

    /**
     * 代理人账号
     */
    @NotNull(message = "{tenant.userName.null}",groups = {InsertValid.class})
    @TableField("user_name")
    private String userName;

    /**
     * 租户描述
     */
    @TableField("tenant_desc")
    private String tenantDesc;

    /**
     * 租户状态
     */
    private String status;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantDesc() {
        return tenantDesc;
    }

    public void setTenantDesc(String tenantDesc) {
        this.tenantDesc = tenantDesc;
    }

    public String getJregLicens() {
        return jregLicens;
    }

    public void setJregLicens(String jregLicens) {
        this.jregLicens = jregLicens;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
