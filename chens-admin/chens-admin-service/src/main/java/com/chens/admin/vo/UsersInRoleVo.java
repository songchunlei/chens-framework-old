package com.chens.admin.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色增加用户
 *
 * @author songchunlei@qq.com
 * @create 2018/3/23
 */
public class UsersInRoleVo implements Serializable{

    /**
     * 角色id
     */
    @NotNull(message = "{role.id.null}")
    private String roleId;

    /**
     * 以逗号隔开的用户id串
     */
    private List<String> users;


    public UsersInRoleVo() {
    }

    public UsersInRoleVo(String roleId, List<String> users) {
        this.roleId = roleId;
        this.users = users;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
