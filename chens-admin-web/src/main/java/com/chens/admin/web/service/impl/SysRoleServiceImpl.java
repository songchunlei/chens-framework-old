package com.chens.admin.web.service.impl;

import com.chens.admin.web.entity.SysRole;
import com.chens.admin.web.mapper.SysRoleMapper;
import com.chens.admin.web.service.ISysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRole> findRoleListByUserId(Long userId) {
        return null;
    }
}
