package com.chens.admin.web.service.impl;

import com.chens.admin.web.entity.SysUser;
import com.chens.admin.web.mapper.SysUserMapper;
import com.chens.admin.web.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }
}
