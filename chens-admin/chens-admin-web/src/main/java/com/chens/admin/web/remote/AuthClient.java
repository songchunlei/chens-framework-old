package com.chens.admin.web.remote;

import com.chens.admin.remote.IAuthClient;
import com.chens.admin.service.IAuthService;
import com.chens.core.entity.SysUser;
import com.chens.core.vo.sys.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/16
 */
@RestController
@RequestMapping(value="/auth")
public class AuthClient implements IAuthClient{

    @Autowired
    private IAuthService authService;

    @Override
    public SysUser findByUserNameAndPassword(@RequestBody AuthRequest authRequest) {
        return authService.findByUsernameAndPassword(authRequest);
    }
}
