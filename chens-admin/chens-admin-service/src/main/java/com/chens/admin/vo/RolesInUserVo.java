package com.chens.admin.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色列表和用户id关系
 *
 * @author songchunlei@qq.com
 * @create 2018/3/17
 */
public class RolesInUserVo implements Serializable{

    /**
     * 用户id
     */
    @NotNull(message = "{sysuser.id.null}")
    private String userId;

    /**
     * 系统角色
     */
    private List<String> sysRoles;

    /**
     * 流程角色
     */
    private List<String> wfRoles;

    public RolesInUserVo(String userId, List<String> sysRoles, List<String> wfRoles) {
        this.userId = userId;
        this.sysRoles = sysRoles;
        this.wfRoles = wfRoles;
    }

    public RolesInUserVo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<String> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public List<String> getWfRoles() {
        return wfRoles;
    }

    public void setWfRoles(List<String> wfRoles) {
        this.wfRoles = wfRoles;
    }
}
