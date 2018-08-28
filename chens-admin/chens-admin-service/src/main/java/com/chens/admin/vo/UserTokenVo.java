package com.chens.admin.vo;


import com.chens.core.vo.UserInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 自定义带用户信息的JWT封装反馈
 *
 * @author songchunlei@qq.com
 * @create 2018/3/19
 */
public class UserTokenVo implements Serializable {
    private String accessToken;
    private List<MenuTree> menus;
    private Map<String, MenuTree> all;
    private UserInfo user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserTokenVo(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public UserTokenVo(String accessToken, List<MenuTree> menus, Map<String, MenuTree> all, UserInfo user) {
        this.accessToken = accessToken;
        this.menus = menus;
        this.all = all;
        this.user = user;
    }

    public List<MenuTree> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuTree> menus) {
        this.menus = menus;
    }

    public Map<String, MenuTree> getAll() {
        return all;
    }

    public void setAll(Map<String, MenuTree> all) {
        this.all = all;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
