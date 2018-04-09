package com.chens.admin.service;

import com.chens.admin.vo.JWTToken;
import com.chens.admin.entity.SysUser;
import com.chens.core.exception.BaseException;
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
     * @param authRequest 请求方法
     * @return SysUser 返回系统用户
     */
    SysUser findByUsernameAndPassword(AuthRequest authRequest) throws BaseException;

    /**
     * 简单密码校验
     * @param authRequest
     * @return boolean
     */
    boolean validate(AuthRequest authRequest) throws BaseException;

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
