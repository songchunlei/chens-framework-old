package com.chens.auth.rpc;

import com.chens.auth.constants.AuthFeignName;
import com.chens.auth.jwt.UAAClaims;
import com.chens.auth.service.ISysTokenService;
import com.chens.core.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * token服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/25
 */
@RestController
@RequestMapping(value="/"+ AuthFeignName.SYS_TOKEN_RPC)
public class SysTokenRpc {

    @Autowired
    private ISysTokenService sysTokenService;

    /**
     * 创建token
     * @param uaaClaims
     * @return
     */
    @PostMapping("/createToken")
    public @ResponseBody
    UserInfo createToken(@RequestBody UAAClaims uaaClaims) {
        return sysTokenService.createToken(uaaClaims);
    }

    /**
     * 创建token-根据用户信息
     * @param userInfo
     * @return
     */
    @PostMapping("/createTokenByUserInfo")
    public @ResponseBody
    UserInfo createTokenByUserInfo(@RequestBody UserInfo userInfo) {
        return sysTokenService.createToken(userInfo);
    }



    /**
     * 解析token
     * @param token
     * @return
     */
    @PostMapping("/parseToken")
    public @ResponseBody
    UserInfo parseToken(@RequestBody String token) throws Exception {
        return sysTokenService.parseToken(token);
    }

    /**
     * 获取用户鉴权的token-key
      * @return
     */
    @PostMapping("/getUserHeaderKey")
    public @ResponseBody String getUserHeaderKey(){
        return sysTokenService.getUserHeaderKey();
    }

    /**
     * 失效token
     * @param token
     * @return
     */
    @PostMapping("/invalidToken")
    public @ResponseBody boolean invalidToken(@RequestBody String token){
        return sysTokenService.invalidToken(token);
    }

}
