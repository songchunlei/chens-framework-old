package com.chens.auth.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.admin.remote.ISysUserClient;
import com.chens.auth.entity.SysToken;
import com.chens.auth.jwt.JwtConfiguration;
import com.chens.auth.jwt.JwtTokenProvider;
import com.chens.auth.jwt.UAAClaims;
import com.chens.auth.mapper.SysTokenMapper;
import com.chens.auth.service.ISysTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.constants.CommonConstants;
import com.chens.core.enums.YesNoEnum;
import com.chens.core.exception.AuthException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.exception.TimeOutException;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
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

    //protected Logger logger = LoggerFactory.getLogger(SysTokenServiceImpl.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Autowired
    private ISysUserClient sysUserClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo createToken(UAAClaims uaaClaims) {
       //logger.info("*******SysTokenService.createToken****************");
        if (uaaClaims != null)
        {
            String newToken  = tokenProvider.createToken(uaaClaims);
            SysToken sysToken = new SysToken(newToken,jwtConfiguration.getExpiration(),YesNoEnum.NO.getCode());
            sysToken.setCreateTime(new Date());
            sysToken.setUpdateTime(new Date());
            sysToken.setUserId(uaaClaims.getAudience());
            this.insert(sysToken);
            return new UserInfo(uaaClaims.getAudience(),uaaClaims.getUser(),uaaClaims.getUserName(),uaaClaims.getTenantId(),newToken);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo createToken(UserInfo userInfo) {
        if (userInfo != null) {
            return this.createToken(this.parseSysUserToClaims(userInfo));
        }
        return null;
    }

    /**
     * 涉及admin-web-rpc调用
     * @param authRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo createToken(AuthRequest authRequest) {
        return this.createToken(sysUserClient.findByUsername(authRequest));
    }

    @Override
    public UserInfo parseToken(String token) {

        //logger.info("*******SysTokenService.parseToken****************");

        UserInfo userInfo;

        try {
            //将token编译
            Claims claims = tokenProvider.parseToken(token);
            //翻译成UserInfo
            userInfo =  new UserInfo(claims.getSubject()
                    ,(String)claims.get(CommonConstants.JWT_TOKEN_USER)
                    , (String)claims.get(CommonConstants.JWT_TOKEN_USERNAME), (String)claims.get(CommonConstants.JWT_TOKEN_TENANTID),token);
        }
        catch (ExpiredJwtException ex){
            throw new TimeOutException(BaseExceptionEnum.TOKEN_EXPIRED);
        }
        catch (SignatureException ex){
            throw new AuthException(BaseExceptionEnum.TOKEN_ERROR);
        }
        catch (IllegalArgumentException ex){
            throw new AuthException(BaseExceptionEnum.TOKEN_IS_NULL);
        }catch (MalformedJwtException e)
        {
            throw new AuthException(BaseExceptionEnum.TOKEN_ERROR);
        } catch (Exception e) {
            throw new AuthException(BaseExceptionEnum.TOKEN_ERROR);
        }
        //判断token是否激活并存在记录里
        SysToken sysToken = new SysToken();
        sysToken.setToken(token);
        sysToken.setIsDelete(YesNoEnum.NO.getCode());
        int count = this.selectCount(new EntityWrapper<>(sysToken));
        if(count<=0)
        {
            throw new TimeOutException(BaseExceptionEnum.TOKEN_EXPIRED);
        }
        return userInfo;
    }

    @Override
    public String getUserHeaderKey() {
        return tokenProvider.getConfiguration().getUserheader();
    }

    @Override
    public boolean invalidToken(String token) {

        //logger.info("*******SysTokenService.invalidToken****************");

        SysToken sysToken = new SysToken();
        sysToken.setToken(token);
        sysToken = this.selectOne(new EntityWrapper(sysToken));

        if(sysToken!=null)
        {
            sysToken.setIsDelete(YesNoEnum.YES.getCode());
            sysToken.setInvalidTime(new Date());
            this.updateById(sysToken);
        }
        return true;
    }

    /**
     * 转换系统用户至Claim
     * @param userInfo
     * @return
     */
    private UAAClaims parseSysUserToClaims(UserInfo userInfo) {

        //logger.info("*******SysTokenService.parseSysUserToClaims****************");

        UAAClaims uaaClaims = new UAAClaims();
        uaaClaims.setId(UUID.randomUUID().toString());
        uaaClaims.setIssuer(jwtConfiguration.getIss());
        uaaClaims.setIssuedAt(new Date());
        uaaClaims.setExpiration(new Date(System.currentTimeMillis() + jwtConfiguration.getExpm() * 1000 * 60));
        uaaClaims.setNotBefore(new Date());

        //系统用户变量
        uaaClaims.setAudience(userInfo.getId());
        uaaClaims.setSubject(userInfo.getId());
        uaaClaims.setUserName(userInfo.getUsername());
        uaaClaims.setUser(userInfo.getName());
        uaaClaims.setTenantId(userInfo.getTenantId());

        return uaaClaims;
    }


}
