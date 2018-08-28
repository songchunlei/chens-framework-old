package com.chens.admin.service;

import com.chens.admin.vo.UserTokenVo;
import com.chens.core.exception.BaseException;
import com.chens.core.vo.AuthRequest;

/**
 * 权限控制
 *
 * @author songchunlei@qq.com
 * @create 2018/3/4
 */
public interface IAuthService {

    /**
     * 简单密码校验
     *
     * @param authRequest
     * @return boolean
     */
    boolean validate(AuthRequest authRequest);

    /**
     * 登录获取JWTToken
     *
     * @param authRequest
     * @return
     * @throws BaseException
     */
    UserTokenVo login(AuthRequest authRequest);

    /**
     * 退出，并使token失效
     *
     * @return
     */
    boolean logout();


    /**
     * 根据token获取JWTToken
     *
     * @param token
     * @return
     */
    UserTokenVo parseToken(String token);

}
