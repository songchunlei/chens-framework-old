package com.chens.admin.web.service;


import com.chens.admin.web.domain.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}