package com.chens.core.auth;

import com.chens.core.constants.CommonConstants;
import com.chens.core.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * jwt方式获取当前用户
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/16
 */
public class JWTPrincipal implements IUserPrincipal{

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String getName(HttpServletRequest request) {
        Claims claims = jwtTokenProvider.parseToken(CommonConstants.AUTH_TOKEN_KEY);
        if(claims!=null)
        {
            return claims.get(CommonConstants.JWT_TOKEN_USERNAME).toString();
        }
        else
        {
            return null;
        }
    }
}
