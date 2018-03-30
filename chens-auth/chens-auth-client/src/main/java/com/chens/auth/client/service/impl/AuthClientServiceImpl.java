package com.chens.auth.client.service.impl;

import com.chens.auth.client.feign.ISysTokenClient;
import com.chens.auth.client.service.IAuthClientService;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.AuthException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 授权客户端服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/30
 */
@Service
public class AuthClientServiceImpl implements IAuthClientService {

    @Autowired
    private ISysTokenClient sysTokenClient;

    @Override
    public UserInfo getUserInfo(HttpServletRequest request) {

        //获取token-key
        String tokenKey = sysTokenClient.getUserHeaderKey();

        //获取token
        String token = request.getHeader(tokenKey);
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(tokenKey)){
                        token = cookie.getValue();
                    }
                }
            }
        }
        if(StringUtils.isEmpty(token))
        {
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
    public String getUserHeaderKey() {
        return sysTokenClient.getUserHeaderKey();
    }
}
