package com.chens.admin.simpleauth;

import com.chens.admin.vo.UserTokenVo;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.UserInfo;

/**
 * 基本授权服务（基本密码账号校验）
 *
 * @author songchunlei@qq.com
 * @create 2018/8/28
 */
public interface ISimpleAuthService {

    /**
     * 登陆
     *
     * @param authRequest
     * @return
     */
    UserTokenVo login(AuthRequest authRequest);

    /**
     * 登出
     *
     * @return
     */
    boolean logout();
}
