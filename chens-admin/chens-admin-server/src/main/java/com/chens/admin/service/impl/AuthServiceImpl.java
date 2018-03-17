package com.chens.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.chens.admin.service.IAuthService;
import com.chens.admin.service.ISysRoleService;
import com.chens.admin.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.vo.AuthRequest;

/**
 * 权限控制实现
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/4
 */
@Service
public class AuthServiceImpl implements IAuthService{

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public SysUser findByUsernameAndPassword(AuthRequest authRequest) throws BaseException{
        SysUser sysUser = sysUserService.findByUsername(authRequest);
        if(sysUser!=null)
        {
            sysUser.setRoles(sysRoleService.findRoleListByUserId(sysUser.getId()));
        }
        return sysUser;
    }

    @Override
    public boolean Validate(AuthRequest authRequest) throws BaseException{
        SysUser sysUser = sysUserService.findByUsername(authRequest);
        if(sysUser!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
