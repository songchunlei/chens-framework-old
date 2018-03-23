package com.chens.admin.service.impl;

import com.chens.admin.service.ISysUserRoleService;
import com.chens.admin.web.vo.QueryRolesByUserId;
import com.chens.core.entity.SysRole;
import com.chens.admin.mapper.SysRoleMapper;
import com.chens.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.entity.SysUserRole;
import com.chens.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    //split分割符号
    private final String SYSROLE_SPLIT_VALUE = ",";

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> findRoleListByUserId(Long userId) {
        return baseMapper.findRoleListByUserId(userId);
    }

    @Transactional
    @Override
    public boolean saveUserRoleList(QueryRolesByUserId queryRolesByUserId) {
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        String sysRoles = queryRolesByUserId.getSysRoles();
        if (StringUtils.isNotEmpty(sysRoles)) {
            String[] checkeds = sysRoles.split(SYSROLE_SPLIT_VALUE);
            for (String s : checkeds) {
                if(StringUtils.isNotEmpty(s)){
                    sysUserRoles.add(new SysUserRole(queryRolesByUserId.getUserId(),Long.getLong(s)));
                }
            }
            return sysUserRoleService.insertBatch(sysUserRoles);
        }

        return false;
    }
}
