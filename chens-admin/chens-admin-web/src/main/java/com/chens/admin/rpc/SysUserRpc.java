package com.chens.admin.rpc;

import com.chens.admin.constants.AdminFeignName;
import com.chens.admin.remote.ISysUserClient;
import com.chens.admin.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.vo.sys.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 授权服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/16
 */
@RestController
@RequestMapping(value="/"+ AdminFeignName.SYS_USER_RPC)
public class SysUserRpc implements ISysUserClient{

    @Autowired
    private ISysUserService sysUserService;

    @Override
    @PostMapping(value = "/findByUsername")
    public @ResponseBody SysUser findByUsername(@RequestBody AuthRequest authRequest) {
        return sysUserService.findByUsername(authRequest);
    }
}
