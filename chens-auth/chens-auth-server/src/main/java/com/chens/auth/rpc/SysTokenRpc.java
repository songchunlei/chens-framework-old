package com.chens.auth.rpc;

import com.chens.auth.vo.IJwtInfo;
import com.chens.auth.jwt.UAAClaims;
import com.chens.auth.service.ISysTokenService;
import com.chens.core.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * token服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/25
 */
@RestController
@RequestMapping(value="/sysTokenRpc")
public class SysTokenRpc {

    @Autowired
    private ISysTokenService sysTokenService;

    /**
     * 创建token
     * @param uaaClaims
     * @return
     */
    @RequestMapping(value = "/createToken", method = RequestMethod.POST)
    public @ResponseBody
    String createToken(@RequestBody UAAClaims uaaClaims) {
        return sysTokenService.createToken(uaaClaims);
    }

    /**
     * 创建token-根据用户
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/createTokenByUser", method = RequestMethod.POST)
    public @ResponseBody
    String createTokenBySysUser(@RequestBody SysUser sysUser) {
        return sysTokenService.createToken(sysUser);
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    @RequestMapping(value="/parseToken",method = RequestMethod.GET)
    public @ResponseBody IJwtInfo parseToken(String token) throws Exception {
        return sysTokenService.parseToken(token);
    }

    /**
     * 获取用户鉴权的token-key
      * @return
     */
    @RequestMapping(value="/getUserHeaderKey",method = RequestMethod.GET)
    public String getUserHeaderKey(){
        return sysTokenService.getUserHeaderKey();
    }

    /**
     * 失效token
     * @param token
     * @return
     */
    @RequestMapping(value="/invalidToken",method = RequestMethod.PUT)
    public boolean invalidToken(String token){
        return sysTokenService.invalidToken(token);
    }

}
