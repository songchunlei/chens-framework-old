package com.chens.admin.web.rpc;

import com.chens.admin.remote.IAuthClient;
import com.chens.admin.service.IAuthService;
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
@RequestMapping(value="/authRpc")
public class AuthRpc implements IAuthClient{

    @Autowired
    private IAuthService authService;

    @Override
    @RequestMapping(value = "/findByUserNameAndPassword", method = RequestMethod.POST)
    public @ResponseBody SysUser findByUserNameAndPassword(@RequestBody AuthRequest authRequest) {
        return authService.findByUsernameAndPassword(authRequest);
    }
}
