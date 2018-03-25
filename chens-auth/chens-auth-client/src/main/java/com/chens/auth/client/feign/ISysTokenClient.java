package com.chens.auth.client.feign;

import com.chens.auth.client.feign.hystrix.SysTokenClientHystrix;
import com.chens.auth.constants.FeignName;
import com.chens.auth.vo.IJwtInfo;
import com.chens.auth.jwt.UAAClaims;
import com.chens.auth.vo.UserInfo;
import com.chens.core.entity.SysUser;
import com.chens.core.vo.sys.AuthRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * token服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/25
 */
@FeignClient(path = FeignName.SYS_TOKEN_RPC,value = "chens-auth-server",fallback = SysTokenClientHystrix.class)
public interface ISysTokenClient {

    /**
     * 创建token
     * @param uaaClaims
     * @return
     */
    @RequestMapping(value="/createToken",method = RequestMethod.POST)
    String createToken(UAAClaims uaaClaims);

    /**
     * 创建token-根据用户
     * @param authRequest
     * @return
     */
    @RequestMapping(value="/createTokenByAuthRequest",method = RequestMethod.POST)
    String createTokenByAuthRequest(AuthRequest authRequest);

    /**
     * 创建token-根据用户
     * @param sysUser
     * @return
     */
    @RequestMapping(value="/createTokenByUser",method = RequestMethod.POST)
    String createTokenByUser(SysUser sysUser);

    /**
     * 解析token成用户
     * @param token
     * @return
     */
    @RequestMapping(value="/parseToken",method = RequestMethod.POST)
    UserInfo parseToken(String token);

    /**
     * 获取用户鉴权的token-key
     * @return
     */
    @RequestMapping(value="/getUserHeaderKey",method = RequestMethod.POST)
    String getUserHeaderKey();

    /**
     * 失效token
     * @param token
     * @return
     */
    @RequestMapping(value="/invalidToken",method = RequestMethod.POST)
    boolean invalidToken(String token);


}
