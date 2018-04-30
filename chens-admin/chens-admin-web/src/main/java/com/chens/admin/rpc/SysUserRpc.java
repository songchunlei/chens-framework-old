package com.chens.admin.rpc;

import com.chens.admin.constants.AdminFeignName;
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
        return this.getUserInfo(sysUserService.findByUsername(authRequest));
    }

    @Override
    public UserInfo findByUserId(@Validated @NotNull(message = "{sysuser.id.null}") String id) {
        return this.getUserInfo(sysUserService.selectById(id));
    }

    /**
     * 转换
     * @param sysUser
     * @return
     */
    private UserInfo getUserInfo(SysUser sysUser)
    {
        if(sysUser!=null)
        {
            return sysUser.getUserInfo();
        }
        return null;
    }
}
