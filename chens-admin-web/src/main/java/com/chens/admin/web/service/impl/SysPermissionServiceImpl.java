package com.chens.admin.web.service.impl;

import com.chens.core.entity.sys.SysPermission;
import com.chens.admin.web.mapper.SysPermissionMapper;
import com.chens.admin.web.service.ISysPermissionService;
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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public List<SysPermission> findPermissionListByRoleId(Long roleId) {
        return null;
    }
}
