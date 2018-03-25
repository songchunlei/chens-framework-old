package com.chens.auth.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.admin.remote.ISysUserClient;
import com.chens.auth.entity.SysToken;
import com.chens.auth.exception.UserTokenException;
import com.chens.auth.exception.UserTokenExceptionEnum;
import com.chens.auth.jwt.JwtConfiguration;
import com.chens.auth.jwt.JwtTokenProvider;
import com.chens.auth.vo.IJwtInfo;
import com.chens.auth.jwt.UAAClaims;
import com.chens.auth.mapper.SysTokenMapper;
import com.chens.auth.service.ISysTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.auth.vo.UserInfo;
import com.chens.core.constants.CommonConstants;
import com.chens.core.entity.SysUser;
import com.chens.core.enums.YesNoEnum;
import com.chens.core.vo.sys.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * 
 *  服务实现类
 *
 * @author chunlei.song@live.com
 * @create 2018-03-25
 */
@Service
public class SysTokenServiceImpl extends ServiceImpl<SysTokenMapper, SysToken> implements ISysTokenService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Autowired
    private ISysUserClient sysUserClient;

    @Override
    @Transactional
    public String createToken(UAAClaims uaaClaims) {
        if (uaaClaims != null)
        {
            String newToken  = tokenProvider.createToken(uaaClaims);
            SysToken sysToken = new SysToken(newToken,jwtConfiguration.getExpiration(),YesNoEnum.YES.getCode());
            sysToken.setCreateTime(new Date());
            sysToken.setUpdateTime(new Date());
            this.insert(sysToken);
            return newToken;
        }
        return null;
    }

    @Override
    @Transactional
    public String createToken(AuthRequest authRequest) {
        return this.createToken(sysUserClient.findByUsername(authRequest));
    }

    @Override
    @Transactional
    public String createToken(SysUser sysUser) {
        if(sysUser!=null)
        {
            return this.createToken(this.parseSysUserToClaims(sysUser));
        }
        return null;
    }

    @Override
    public UserInfo parseToken(String token) throws Exception {
        try {
            //将token编译
            Claims claims = tokenProvider.parseToken(token);
            //翻译成UserInfo
            return new UserInfo(claims.getSubject()
                    ,(String)claims.get(CommonConstants.JWT_TOKEN_USER)
                    , (String)claims.get(CommonConstants.JWT_TOKEN_USERNAME), (String)claims.get(CommonConstants.JWT_TOKEN_TENANTID));
        }catch (ExpiredJwtException ex){
            throw new UserTokenException(UserTokenExceptionEnum.TOKEN_EXPIRED);
        }catch (SignatureException ex){
            throw new UserTokenException(UserTokenExceptionEnum.TOKEN_ERROR);
        }catch (IllegalArgumentException ex){
            throw new UserTokenException(UserTokenExceptionEnum.TOKEN_IS_NULL);
        }
    }

    @Override
    public String getUserHeaderKey() {
        return tokenProvider.getConfiguration().getUserheader();
    }

    @Override
    public boolean invalidToken(String token) {
        SysToken sysToken = new SysToken();
        sysToken.setToken(token);
        sysToken = this.selectOne(new EntityWrapper(sysToken));
        if(sysToken!=null)
        {
            sysToken.setIsActive(YesNoEnum.NO.getCode());
            sysToken.setInvalidTime(new Date());
            this.updateById(sysToken);
        }
        return false;
    }


    /**
     * 转换系统用户至Claim
     * @param sysUser
     * @return
     */
    private UAAClaims parseSysUserToClaims(SysUser sysUser) {

        UAAClaims uaaClaims = new UAAClaims();
        uaaClaims.setId(UUID.randomUUID().toString());
        uaaClaims.setIssuer(jwtConfiguration.getIss());
        uaaClaims.setIssuedAt(new Date());
        uaaClaims.setExpiration(new Date(System.currentTimeMillis() + jwtConfiguration.getExpm() * 1000 * 60));
        uaaClaims.setNotBefore(new Date());

        //系统用户变量
        uaaClaims.setAudience(sysUser.getId());
        uaaClaims.setSubject(sysUser.getId());
        uaaClaims.setUserName(sysUser.getUsername());
        uaaClaims.setUser(sysUser.getName());
        uaaClaims.setTenantId(sysUser.getTenantId());

        return uaaClaims;
    }


}
