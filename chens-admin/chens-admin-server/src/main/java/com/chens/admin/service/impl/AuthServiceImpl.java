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
        /*
        if(authRequest==null)
        {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }

        if(StringUtils.isEmpty(authRequest.getUserName()))
        {
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_NO_USERNAME);
        }

        if(StringUtils.isEmpty(authRequest.getPassword()))
        {
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_NO_PASSWORD);
        }

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
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
        }
        */
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
