package com.chens.admin.service.impl;

import java.util.List;

import com.chens.core.vo.AuthRequest;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.admin.mapper.SysUserMapper;
import com.chens.admin.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser findByUsername(AuthRequest authRequest) throws BaseException{
        SysUser query = new SysUser();
        query.setUsername(authRequest.getUserName());
        query.setPassword(authRequest.getPassword());
        List<SysUser> users = this.selectList(new EntityWrapper<>(query));
        if(users!=null && users.size()>0)
        {
            for (SysUser user:users) {
                return user;
            }
        }
        return null;
    }
}
