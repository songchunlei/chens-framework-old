package com.chens.admin.web.service;

import com.chens.core.entity.sys.SysUser;

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
}
