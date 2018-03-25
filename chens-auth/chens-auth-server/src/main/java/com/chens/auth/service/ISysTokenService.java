package com.chens.auth.service;

import com.chens.auth.entity.SysToken;
import com.baomidou.mybatisplus.service.IService;
import com.chens.auth.vo.IJwtInfo;
import com.chens.auth.jwt.UAAClaims;
import com.chens.core.entity.SysUser;

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
	String createToken(UAAClaims uaaClaims);

    /**
     * 创建token-系统用户
     * @param sysUser
     * @return
     */
	String createToken(SysUser sysUser);

    /**
     * 解析token
     * @param token
     * @return
     */
    IJwtInfo parseToken(String token) throws Exception;


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
