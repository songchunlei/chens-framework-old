package com.chens.auth.client.service;

import com.chens.core.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权客户端服务
 *
 * @author songchunlei@qq.com
 * @create 2018/3/30
 */
public interface IAuthClientService {

    /**
     * 从request获取用户信息并存入Thread缓存
     * @param request
     * @return
     */
    UserInfo getUserInfo(HttpServletRequest request);

    /**
     * 简单鉴权
     * 直接从request读用户信息
     * @param request
     * @return
     */
    UserInfo getUserInfoBySimple(HttpServletRequest request);

    /**
     * 获取用户鉴权的token-key
     * @return
     */
    String getUserHeaderKey();

    /**
     * 获取token
     * @param request
     * @return
     */
    String getToken(HttpServletRequest request);
}
