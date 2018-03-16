package com.chens.admin.remote;

import com.chens.admin.remote.IAuthClient;
import com.chens.admin.web.service.IAuthService;
import com.chens.core.entity.SysUser;
import com.chens.core.vo.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权接口实现
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/13
 */
@RestController
public class AuthClientImpl implements IAuthClient {

    @Autowired
    private IAuthService authService;

    @Override
    public SysUser findByUserNameAndPassword(@RequestBody AuthRequest authRequest) {
        return authService.findByUsernameAndPassword(authRequest);
    }
}
