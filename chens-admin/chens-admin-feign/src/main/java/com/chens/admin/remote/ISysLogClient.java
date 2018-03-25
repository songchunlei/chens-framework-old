package com.chens.admin.remote;

import com.chens.admin.remote.hystrix.SysLogClientHystrix;
import com.chens.core.entity.SysLog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @auther songchunlei
 * @create 2018/3/24
 */
@FeignClient(path = "sysLogRpc",value = "chens-admin-web",fallback = SysLogClientHystrix.class)
public interface ISysLogClient {

    @RequestMapping(value="/create",method = RequestMethod.POST)
    boolean create(@RequestBody SysLog sysLog);

}
