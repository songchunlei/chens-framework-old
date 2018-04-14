package com.chens.admin.remote;


import com.chens.admin.constants.AdminFeignName;
import com.chens.admin.remote.hystrix.SysUserClientHystrix;
import com.chens.admin.entity.SysUser;
import com.chens.core.vo.AuthRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 用户查询接口
 *
 * @author songchunlei@qq.com
 * @create 2018/3/8
 */
@FeignClient(path = AdminFeignName.SYS_USER_RPC,value = AdminFeignName.CHENS_ADMIN_WEB_SERVER_NAME,fallback = SysUserClientHystrix.class)
public interface ISysUserClient {

    @RequestMapping(value="/findByUsername",method = RequestMethod.POST)
    SysUser findByUsername(@RequestBody AuthRequest authRequest);
}
