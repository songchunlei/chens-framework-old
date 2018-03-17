package com.chens.core.vo;

import com.chens.core.entity.SysTenant;
import com.chens.core.entity.SysUser;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注册租户
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
public class RegisterTenant implements Serializable {

    /**
     * 租户名称
     */
    @NotNull
    private String tenantName;

    private String tenantDesc;

    /**
     * 营业执照
     */
    @NotNull
    private String jregLicens;

    /**
     * 代理人账户（一般是手机或者邮箱）
     */
    @NotNull
    private String userName;

    /**
     * 代码人密码
     */
    @NotNull
    private String password;

    /**
     * 代理人手机
     */
    @NotNull
    private String phone;

    /**
     * 代理人邮箱
     */
    @NotNull
    private String email;

    public RegisterTenant() {
    }

    public RegisterTenant(String tenantName, String tenantDesc, String jregLicens, String userName,String password, String phone, String email) {
        this.tenantName = tenantName;
        this.tenantDesc = tenantDesc;
        this.jregLicens = jregLicens;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
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

    public SysTenant getSysTenant(){
        SysTenant sysTenant = new SysTenant();
        sysTenant.setUserName(this.userName);
        sysTenant.setJregLicens(this.jregLicens);
        sysTenant.setTenantDesc(this.tenantDesc);
        sysTenant.setTenantName(this.tenantName);
        return sysTenant;
    }

    public SysUser getSysUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(this.userName);
        sysUser.setPassword(this.password);
        sysUser.setPhone(this.phone);
        sysUser.setEmail(this.email);
        return sysUser;
    }
}
