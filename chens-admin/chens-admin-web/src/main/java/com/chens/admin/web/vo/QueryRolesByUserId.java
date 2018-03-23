package com.chens.admin.web.vo;

import java.io.Serializable;

/**
 * 角色列表和用户id关系
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/17
 */
public class QueryRolesByUserId implements Serializable{

    //用户id
    private Long userId;
    //以逗号隔开的系统角色串
    private String sysRoles;
    //以逗号隔开的流程角色串
    private String wfRoles;

    public QueryRolesByUserId(Long userId, String sysRoles, String wfRoles) {
        this.userId = userId;
        this.sysRoles = sysRoles;
        this.wfRoles = wfRoles;
    }

    public QueryRolesByUserId() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(String sysRoles) {
        this.sysRoles = sysRoles;
    }

    public String getWfRoles() {
        return wfRoles;
    }

    public void setWfRoles(String wfRoles) {
        this.wfRoles = wfRoles;
    }
}
