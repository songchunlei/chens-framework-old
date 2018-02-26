package com.chens.admin.web.service.impl;


import com.chens.admin.web.dao.UserInfoDao;
import com.chens.admin.web.domain.UserInfo;
import com.chens.admin.web.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}