package com.chens.admin.service.impl;

import com.chens.core.entity.SysPermission;
import com.chens.admin.web.mapper.SysPermissionMapper;
import com.chens.admin.web.service.ISysPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.vo.ZTree;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> findPermissionListByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<ZTree> tree() {
        return sysPermissionMapper.tree();
    }
}
