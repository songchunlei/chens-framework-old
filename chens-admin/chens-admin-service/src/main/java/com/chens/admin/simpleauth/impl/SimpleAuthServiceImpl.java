package com.chens.admin.simpleauth.impl;

import com.chens.admin.entity.SysUser;
import com.chens.admin.handler.UserHandler;
import com.chens.admin.service.ISysUserService;
import com.chens.admin.service.IUserTokenVoService;
import com.chens.admin.simpleauth.ISimpleAuthService;
import com.chens.admin.vo.UserTokenVo;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 基本授权服务实现
 *
 * @author songchunlei@qq.com
 * @create 2018/8/28
 */
@Component("simpleAuthService")
public class SimpleAuthServiceImpl implements ISimpleAuthService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IUserTokenVoService userTokenVoService;

    @Override
    public UserTokenVo login(AuthRequest authRequest) {
        SysUser sysUser = sysUserService.findByUsername(authRequest);
        if (sysUser == null) {
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
        }

        //简单密码账号登陆不走token
        UserInfo userInfo = UserHandler.getUserInfoBySysUser(sysUser, null);
        //存入缓存，用于鉴权校验
        BaseContextHandler.setUserInfo(userInfo);

        return userTokenVoService.getUserTokenVo(userInfo);
    }

    @Override
    public boolean logout() {
        BaseContextHandler.clear();
        return true;
    }


}
