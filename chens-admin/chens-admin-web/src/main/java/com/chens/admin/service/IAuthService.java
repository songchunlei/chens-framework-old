package com.chens.admin.service;

import com.chens.auth.client.vo.JWTToken;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.vo.sys.AuthRequest;

/**
 * 权限控制
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/4
 */
public interface IAuthService {
    /**
     * 根据账户获取用户-含账号密码，内部使用方法,不要往前端传
     * @param authRequest
     * @return SysUser
     */
    SysUser findByUsernameAndPassword(AuthRequest authRequest) throws BaseException;

    /**
     * 简单密码校验
     * @param authRequest
     * @return boolean
     */
    boolean Validate(AuthRequest authRequest) throws BaseException;

    /**
     * 登录获取JWTToken
     * @param authRequest
     * @return
     * @throws BaseException
     */
    JWTToken login(AuthRequest authRequest) throws Exception;

    /**
     * 退出，并使token失效
     * @return
     */
    boolean logout();


    /**
     * 根据token获取JWTToken
     * @param token
     * @return
     */
    JWTToken parseToken(String token) throws Exception;

}
