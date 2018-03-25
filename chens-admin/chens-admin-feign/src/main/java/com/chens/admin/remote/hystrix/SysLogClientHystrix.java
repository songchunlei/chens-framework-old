package com.chens.admin.remote.hystrix;


import com.chens.admin.remote.ISysLogClient;
import com.chens.core.entity.SysLog;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统日志熔断
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/24
 */
public class SysLogClientHystrix implements ISysLogClient{

    private static final Logger logger = LoggerFactory.getLogger(AuthClientHystrix.class);

    @Override
    public boolean create(@RequestBody SysLog sysLog) {
        logger.error("----保存日志出错-----");
        throw new BaseException(BaseExceptionEnum.REQUEST_NULL.getCode(), "保存日志出错");
    }
}
