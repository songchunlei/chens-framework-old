package com.chens.admin.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.core.entity.SysUser;
import com.chens.admin.web.service.IAuthService;
import com.chens.admin.web.service.ISysRoleService;
import com.chens.admin.web.service.ISysUserService;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限控制实现
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/4
 */
public class AuthServiceImpl implements IAuthService{

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public SysUser findByUsername(String username) {
        SysUser sysUser = sysUserService.findByUsername(username);
        sysUser.setRoles(sysRoleService.findRoleListByUserId(sysUser.getId()));
        return sysUser;
    }

    @Override
    public boolean Validate(AuthRequest authRequest) {
        SysUser query = new SysUser();
        query.setUsername(authRequest.getUserName());
        query.setPassword(authRequest.getPassword());
        int count = sysUserService.selectCount(new EntityWrapper<>(query));
        if(count>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
