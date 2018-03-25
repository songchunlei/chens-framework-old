package com.chens.admin.remote;


import com.chens.admin.constants.FeignName;
import com.chens.admin.remote.hystrix.SysUserClientHystrix;
import com.chens.core.entity.SysUser;
import com.chens.core.vo.sys.AuthRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 用户查询接口
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/8
 */
@FeignClient(path = FeignName.SYS_USER_RPC,value = "chens-admin-web",fallback = SysUserClientHystrix.class)
public interface ISysUserClient {

    @RequestMapping(value="/findByUsername",method = RequestMethod.POST)
    SysUser findByUsername(@RequestBody AuthRequest authRequest);
}
