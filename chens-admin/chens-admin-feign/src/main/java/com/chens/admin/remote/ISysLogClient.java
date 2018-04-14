package com.chens.admin.remote;

import com.chens.admin.constants.AdminFeignName;
import com.chens.admin.remote.hystrix.SysLogClientHystrix;
import com.chens.admin.entity.SysLog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author songchunlei
 * @create 2018/3/24
 */
@FeignClient(path = AdminFeignName.SYS_LOG_RPC,value = AdminFeignName.CHENS_ADMIN_WEB_SERVER_NAME,fallback = SysLogClientHystrix.class)
public interface ISysLogClient {

    /**
     * 创建日志
     * @param sysLog
     * @return
     */
    @RequestMapping(value="/create",method = RequestMethod.POST)
    boolean create(@RequestBody SysLog sysLog);

}
