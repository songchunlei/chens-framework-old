package com.chens.admin.service.impl;


import com.chens.admin.enums.SysMenuEnum;
import com.chens.admin.handler.UserHandler;
import com.chens.admin.service.ISysMenuService;
import com.chens.admin.service.IUserTokenVoService;
import com.chens.auth.client.feign.ISysTokenClient;
import com.chens.core.vo.UserInfo;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.admin.entity.SysMenu;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.core.util.TreeUtil;
import com.chens.admin.vo.UserTokenVo;
import com.chens.admin.vo.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.chens.admin.service.IAuthService;
import com.chens.admin.service.ISysUserService;
import com.chens.admin.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.vo.AuthRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限控制实现
 *
 * @author songchunlei@qq.com
 * @create 2018/3/4
 */
@Service
public class AuthServiceImpl implements IAuthService {

    //protected Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private ISysTokenClient sysTokenClient;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IUserTokenVoService userTokenVoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserTokenVo login(AuthRequest authRequest) {
        if (authRequest != null) {
            //logger.info("*******AuthService.login****************");
            SysUser sysUser = sysUserService.findByUsername(authRequest);
            if (sysUser == null) {
                throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
            }
            UserInfo userInfo = sysTokenClient.createTokenByUserInfo(UserHandler.getUserInfoBySysUser(sysUser, null));
            return userTokenVoService.getUserTokenVo(userInfo);
        }
        throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean logout() {
        //logger.info("*******AuthService.loginout:****************"+token);
        String token = BaseContextHandler.getToken();
        if (StringUtils.isNotEmpty(token)) {
            sysTokenClient.invalidToken(token);
        }
        return true;
    }


    @Override
    public UserTokenVo parseToken(String token) {
        //解析jwtInfo
        UserInfo userInfo = sysTokenClient.parseToken(token);
        return userTokenVoService.getUserTokenVo(userInfo);
    }


    @Override
    public boolean validate(AuthRequest authRequest) {
        SysUser sysUser = sysUserService.findByUsername(authRequest);
        if (sysUser != null) {
            return true;
        } else {
            return false;
        }
    }

}
