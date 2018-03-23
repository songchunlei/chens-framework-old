package com.chens.admin.web.vo;

import java.io.Serializable;

/**
 * 角色增加用户
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/23
 */
public class AddUsersInRole implements Serializable{

    //角色id
    private Long roleId;
    //以逗号隔开的用户id串
    private String users;
    //以逗号隔开的系统角色串


    public AddUsersInRole(Long roleId, String users) {
        this.roleId = roleId;
        this.users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
