package com.chens.admin.web.service.impl;

import com.chens.core.entity.sys.SysUser;;
import com.chens.admin.web.service.IAuthService;
import com.chens.admin.web.service.ISysRoleService;
import com.chens.admin.web.service.ISysUserService;
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
}
