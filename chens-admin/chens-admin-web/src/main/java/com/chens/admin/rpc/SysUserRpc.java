package com.chens.admin.rpc;

import com.chens.admin.constants.AdminFeignName;
import com.chens.admin.handler.UserHandler;
import com.chens.admin.remote.ISysUserClient;
import com.chens.admin.service.ISysUserService;
import com.chens.admin.entity.SysUser;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 授权服务
 *
 * @author songchunlei@qq.com
 * @create 2018/3/16
 */
@RestController
@RequestMapping(value="/"+ AdminFeignName.SYS_USER_RPC)
public class SysUserRpc implements ISysUserClient{

    @Autowired
    private ISysUserService sysUserService;


    @Override
    @PostMapping(value = "/findByUsername")
    public @ResponseBody UserInfo findByUsername(@Validated @RequestBody AuthRequest authRequest) {
        return UserHandler.getUserInfoBySysUser(sysUserService.findByUsername(authRequest),null);
    }

    @Override
    public UserInfo findByUserId(@Validated @NotNull(message = "{sysuser.id.null}") String id) {
        return UserHandler.getUserInfoBySysUser(sysUserService.selectById(id),null);
    }
}
