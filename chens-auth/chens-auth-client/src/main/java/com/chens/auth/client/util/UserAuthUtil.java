package com.chens.auth.client.util;

import com.chens.auth.client.exception.UserTokenException;
import com.chens.auth.client.exception.UserTokenExceptionEnum;
import com.chens.auth.client.jwt.IJwtInfo;
import com.chens.auth.client.jwt.JwtConfiguration;
import com.chens.auth.client.jwt.JwtTokenProvider;
import com.chens.auth.client.jwt.UAAClaims;
import com.chens.auth.client.vo.UserInfo;
import com.chens.core.constants.CommonConstants;
import com.chens.core.entity.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.UUID;

/**
 * 用户鉴权
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/22
 */
@Configuration
public class UserAuthUtil {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserAuthUtil(JwtTokenProvider jwtTokenProvider) {
        this.setJwtTokenProvider(jwtTokenProvider);
    }

    public JwtTokenProvider getJwtTokenProvider() {
        return jwtTokenProvider;
    }

    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * 从token获取用户信息
     * @param token
     * @return
     * @throws Exception
     */
    public IJwtInfo getUserInfo(String token) throws Exception{


        try {
            //将token编译
            Claims claims = jwtTokenProvider.parseToken(token);
            //翻译成UserInfo
            return new UserInfo(Long.valueOf(claims.getSubject())
                    , Long.valueOf(claims.get(CommonConstants.JWT_TOKEN_TENANTID).toString())
                    ,(String)claims.get(CommonConstants.JWT_TOKEN_USER)
                    , (String)claims.get(CommonConstants.JWT_TOKEN_USERNAME));
        }catch (ExpiredJwtException ex){
            throw new UserTokenException(UserTokenExceptionEnum.TOKEN_EXPIRED);
        }catch (SignatureException ex){
            throw new UserTokenException(UserTokenExceptionEnum.TOKEN_ERROR);
        }catch (IllegalArgumentException ex){
            throw new UserTokenException(UserTokenExceptionEnum.TOKEN_IS_NULL);
        }



    }


    /**
     * 根据登录用户创建token
     * @param sysUser
     * @return
     */
    public String  createToken(SysUser sysUser) {
        return jwtTokenProvider.createToken(this.parseSysUserToClaims(sysUser));
    }

    /**
     * 转换系统用户至Claim
     * @param sysUser
     * @return
     */
    private UAAClaims parseSysUserToClaims(SysUser sysUser) {

        JwtConfiguration jwtConfiguration =  jwtTokenProvider.getConfiguration();

        UAAClaims uaaClaims = new UAAClaims();
        uaaClaims.setIssuer(jwtConfiguration.getIss());
        uaaClaims.setIssuedAt(new Date());
        uaaClaims.setAudience(String.valueOf(sysUser.getId()));
        uaaClaims.setId(UUID.randomUUID().toString());
        uaaClaims.setExpiration(new Date(System.currentTimeMillis() + jwtConfiguration.getExpm() * 1000 * 60));
        uaaClaims.setNotBefore(new Date());

        //系统用户变量
        uaaClaims.setSubject(String.valueOf(sysUser.getId()));
        uaaClaims.setUserName(sysUser.getUsername());
        uaaClaims.setUser(sysUser.getName());
        uaaClaims.setTenantId(sysUser.getTenantId().toString());

        return uaaClaims;
    }
}
