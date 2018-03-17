package com.chens.admin.remote.hystrix;

import com.chens.admin.remote.IAuthClient;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.sys.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 熔断
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/14
 */
@Component
public class AuthClientHystrix implements IAuthClient {
    private static final Logger logger = LoggerFactory.getLogger(AuthClientHystrix.class);
    @Override
    public SysUser findByUserNameAndPassword(@RequestBody AuthRequest authRequest) {
        logger.error("----调用鉴权出错-----");
        throw new BaseException(BaseExceptionEnum.REQUEST_NULL.getCode(), "调用鉴权出错");
    }
}
