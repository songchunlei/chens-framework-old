package com.chens.admin.vo;

import com.chens.core.constants.CommonConstants;
import com.chens.admin.entity.SysTenant;
import com.chens.admin.entity.SysUser;
import com.chens.admin.enums.TenantType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 注册租户
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
public class RegisterTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户名称
     */
    @NotNull(message = "{tenant.name.null}")
    private String tenantName;

    private String tenantDesc;

    /**
     * 营业执照
     */
    @NotNull(message = "{tenant.jregLicens.null}")
    private String jregLicens;

    /**
     * 代理人账户（一般是手机或者邮箱）
     */
    @NotNull(message = "{tenant.userName.null}")
    private String userName;

    /**
     * 代理人姓名
     */
    @NotNull(message = "{tenant.user.null}")
    private String user;

    /**
     * 代码人密码
     */
    @NotNull(message = "{tenant.password.null}")
    private String password;

    /**
     * 代理人手机
     */
    @NotNull(message = "{tenant.phone.null}")
    private String phone;

    /**
     * 代理人邮箱
     */
    @NotNull(message = "{tenant.email.null}")
    private String email;

    public RegisterTenant() {
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getJregLicens() {
        return jregLicens;
    }

    public void setJregLicens(String jregLicens) {
        this.jregLicens = jregLicens;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTenantDesc() {
        return tenantDesc;
    }

    public void setTenantDesc(String tenantDesc) {
        this.tenantDesc = tenantDesc;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SysTenant getSysTenant(){
        SysTenant sysTenant = new SysTenant();
        sysTenant.setUserName(this.userName);
        sysTenant.setJregLicens(this.jregLicens);
        sysTenant.setTenantDesc(this.tenantDesc);
        sysTenant.setTenantName(this.tenantName);
        sysTenant.setCreateTime(new Date());
        sysTenant.setUpdateTime(new Date());
        sysTenant.setCreateBy(CommonConstants.SYSTEM_USER_ID);
        sysTenant.setUpdateBy(CommonConstants.SYSTEM_USER_ID);
        sysTenant.setStatus(TenantType.NEW.getCode());
        return sysTenant;
    }

    public SysUser getSysUser(){
        SysUser sysUser = new SysUser();
        sysUser.setName(this.user);
        sysUser.setUsername(this.userName);
        sysUser.setPassword(this.password);
        sysUser.setPhone(this.phone);
        sysUser.setEmail(this.email);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setCreateBy(CommonConstants.SYSTEM_USER_ID);
        sysUser.setUpdateBy(CommonConstants.SYSTEM_USER_ID);
        return sysUser;
    }
}
