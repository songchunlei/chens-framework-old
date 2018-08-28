package com.chens.auth.client.service.impl;

import com.chens.admin.remote.ISysUserClient;
import com.chens.auth.client.feign.ISysTokenClient;
import com.chens.auth.client.service.IAuthClientService;
import com.chens.auth.constants.AuthConstants;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.AuthException;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 授权客户端服务
 *
 * @author songchunlei@qq.com
 * @create 2018/3/30
 */
@Service
public class AuthClientServiceImpl implements IAuthClientService {

    @Autowired
    private ISysTokenClient sysTokenClient;

    @Override
    public UserInfo getUserInfo(HttpServletRequest request) {

        //获取token
        String token = this.getToken(request);

        //token为空报错
        if (StringUtils.isEmpty(token)) {
            throw new AuthException(BaseExceptionEnum.TOKEN_ERROR);
        }

        //解析token
        UserInfo userInfo = sysTokenClient.parseToken(token);

        //存入缓存
        BaseContextHandler.setUserName(userInfo.getUsername());
        BaseContextHandler.setName(userInfo.getName());
        BaseContextHandler.setUserId(userInfo.getId());
        BaseContextHandler.setTenantId(userInfo.getTenantId());
        BaseContextHandler.setToken(token);

        //返回
        return userInfo;

    }

    @Override
    public UserInfo getUserInfoBySimple(HttpServletRequest request) {

        //获取token
        String token = request.getHeader(this.getUserHeaderKey());
        //获取用户id信息
        String userId = request.getHeader(AuthConstants.KEY_USER_ID);

        //补偿逻辑，当没有用户id但是有token，继续拿token鉴权
        if (StringUtils.isEmpty(userId) && StringUtils.isNotEmpty(token)) {
            return getUserInfo(request);
        }

        //获取用户信息
        String userName = request.getHeader(AuthConstants.KEY_USER_NAME);
        String tenantId = request.getHeader(AuthConstants.KEY_TENANT_ID);
        //反编译用户名称
        String name = null;
        try {
            name = URLDecoder.decode(request.getHeader(AuthConstants.KEY_NAME), CommonConstants.CHARACTER_UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(BaseExceptionEnum.DATA_REQUEST_ERROR.getCode(), e.getMessage());
        }


        //当用户id为空，则报错
        if (StringUtils.isEmpty(userId)) {
            throw new AuthException(BaseExceptionEnum.AUTH_REQUEST_SIMPLE_ERROR);
        }

        //存入缓存
        UserInfo userInfo = new UserInfo(userId, name, userName, tenantId, token);
        BaseContextHandler.setUserInfo(userInfo);

        return userInfo;
    }


    @Override
    public String getUserHeaderKey() {
        return sysTokenClient.getUserHeaderKey();
    }

    @Override
    public String getToken(HttpServletRequest request) {
        //获取token-key
        String tokenKey = sysTokenClient.getUserHeaderKey();

        //获取token
        String token = request.getHeader(tokenKey);

        //当没有token则从cookie取
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(tokenKey)) {
                        token = cookie.getValue();
                    }
                }
            }
        }

        return token;
    }


}
