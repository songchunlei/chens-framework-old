package com.chens.auth.client.feign;

import com.chens.auth.client.feign.hystrix.SysTokenClientHystrix;
import com.chens.auth.vo.IJwtInfo;
import com.chens.auth.jwt.UAAClaims;
import com.chens.core.entity.SysUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * token服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/25
 */
@FeignClient(path = "sysTokenRpc",value = "chens-auth-server",fallback = SysTokenClientHystrix.class)
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
     * @param sysUser
     * @return
     */
    @RequestMapping(value="/createTokenByUser",method = RequestMethod.POST)
    String createTokenByUser(SysUser sysUser);

    /**
     * 解析token
     * @param token
     * @return
     */
    @RequestMapping(value="/parseToken",method = RequestMethod.GET)
    IJwtInfo parseToken(String token);

    /**
     * 获取用户鉴权的token-key
     * @return
     */
    @RequestMapping(value="/getUserHeaderKey",method = RequestMethod.GET)
    String getUserHeaderKey();


}
