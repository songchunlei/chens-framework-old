package com.chens.admin.web.service;

import com.chens.core.entity.SysUser;
import com.chens.core.vo.AuthRequest;

/**
 * 权限控制
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/4
 */
public interface IAuthService {
    /**
     * 根据账户获取用户-含账号密码，内部使用方法,不要往前端传
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 简单密码校验
     * @param authRequest
     * @return
     */
    boolean Validate(AuthRequest authRequest);

}
