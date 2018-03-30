package com.chens.auth.service;

import com.chens.admin.entity.SysUser;
import com.chens.auth.entity.SysToken;
import com.baomidou.mybatisplus.service.IService;
import com.chens.auth.jwt.UAAClaims;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.UserInfo;

/**
 *
 *  服务接口
 *
 * @author chunlei.song@live.com
 * @create 2018-03-25
 */
public interface ISysTokenService extends IService<SysToken> {
    /**
     * 创建token
     * @param uaaClaims
     * @return
     */
    UserInfo createToken(UAAClaims uaaClaims);

    /**
     * 创建token-登录请求
     * @param authRequest
     * @return
     */
    UserInfo createToken(AuthRequest authRequest);

    /**
     * 创建token-系统用户
     * @param userInfo
     * @return
     */
    UserInfo createToken(UserInfo userInfo);

    /**
     * 解析token
     * @param token
     * @return
     */
    UserInfo parseToken(String token);


    /**
     * 获取用户鉴权的token-key
     * @return
     */
    String getUserHeaderKey();

    /**
     * 失效token
     * @param token
     * @return
     */
    boolean invalidToken(String token);

}
