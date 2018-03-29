package com.chens.auth.client.feign.hystrix;

import com.chens.auth.client.feign.ISysTokenClient;
import com.chens.auth.jwt.UAAClaims;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.UserInfo;
import org.springframework.stereotype.Component;

/**
 * 降级熔断
 * 暂未做降级处理，待优化
 * @auther songchunlei@qq.com
 * @create 2018/3/25
 */
@Component
public class SysTokenClientHystrix implements ISysTokenClient{
    @Override
    public UserInfo createToken(UAAClaims uaaClaims) {
        throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
    }

    @Override
    public UserInfo createTokenByUserInfo(UserInfo userInfo) {
        throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
    }

    @Override
    public UserInfo parseToken(String token) throws Exception {
        throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
    }

    @Override
    public String getUserHeaderKey() {
        throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
    }

    @Override
    public boolean invalidToken(String token) {
        throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
    }
}
