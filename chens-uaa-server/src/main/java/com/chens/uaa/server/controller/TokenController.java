package com.chens.uaa.server.controller;

import com.chens.admin.remote.IAuthClient;
import com.chens.core.entity.SysUser;
import com.chens.core.jwt.JwtConfiguration;
import com.chens.core.jwt.JwtTokenProvider;
import com.chens.core.jwt.UAAClaims;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.JWTToken;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * 鉴权
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/12
 */
@RestController
@RequestMapping("/uaa")
public class TokenController extends BaseController{
    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IAuthClient authClient;

    // 获取一个根据账户和密码获取token
    @PostMapping("/token/getTokenByUserName")
    public ResponseEntity<Result> getTokenByUserName(@RequestBody AuthRequest authRequest) {
        SysUser sysUser = authClient.findByUserNameAndPassword(authRequest);
        if (sysUser == null) {
            return doError("账号或密码错误");
        }
        return doSuccess(new JWTToken(jwtTokenProvider.createToken(parseClaims(sysUser))));
    }

    // 将token反解出来，看看里面的内容；
    // 仅用于开发场景
    @RequestMapping("/token/parse")
    public Claims parseToken(String token) {
        return jwtTokenProvider.parseToken(token);
    }

    // UAAClaims这个对象就是token中的内容
    private UAAClaims parseClaims(SysUser sysUser) {
        UAAClaims uaaClaims = new UAAClaims();
        uaaClaims.setIssuer(jwtConfiguration.getIss());
        uaaClaims.setIssuedAt(new Date());
        uaaClaims.setAudience(String.valueOf(sysUser.getId()));
        uaaClaims.setId(UUID.randomUUID().toString());
        uaaClaims.setUserName(sysUser.getUsername());
        uaaClaims.setExpiration(new Date(System.currentTimeMillis() + jwtConfiguration.getExpm() * 1000 * 60));
        //uaaClaims.setEmail(userDomain.getEmail());
        //uaaClaims.setPhone(userDomain.getPhone());
        uaaClaims.setSubject(String.valueOf(sysUser.getId()));
        uaaClaims.setNotBefore(new Date());
        return uaaClaims;

    }
}
