package com.chens.auth.client.service;

import com.chens.core.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权客户端服务
 *
 * @auther songchunlei@qq.com
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
     * 获取用户鉴权的token-key
     * @return
     */
    String getUserHeaderKey();
}
