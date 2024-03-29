package com.chens.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.admin.entity.SysUserRole;
import com.chens.admin.mapper.SysUserRoleMapper;
import com.chens.admin.service.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.admin.vo.RolesInUserVo;
import com.chens.admin.vo.UsersInRoleVo;
import com.chens.core.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *  服务实现类
 *
 * @author chunlei.song@live.com
 * @create 2018-03-17
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    private final String DEFAULT_USERS_SPLIT_FLG=",";
    private final String DEFAULT_ROLES_SPLIT_FLG=",";



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRolesInUser(RolesInUserVo rolesInUserVo) {
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        List<String> sysRoles = rolesInUserVo.getSysRoles();
        if (!CollectionUtils.isEmpty(sysRoles)) {
            for (String s : sysRoles) {
                if(StringUtils.isNotEmpty(s)){
                    sysUserRoles.add(new SysUserRole(rolesInUserVo.getUserId(),s));
                }
            }
            return this.insertBatch(sysUserRoles);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUsersInRole(UsersInRoleVo usersInRoleVo) {
        boolean  flagUserRole = false;
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        List<String> users =  usersInRoleVo.getUsers();
        if (!CollectionUtils.isEmpty(users)) {
            for (String user : users) {
                SysUserRole r = new SysUserRole(user,usersInRoleVo.getRoleId());
                sysUserRoles.add(r);
            }
            // 将选中角色id进行保存处理
            flagUserRole = this.insertBatch(sysUserRoles);
        }
        return flagUserRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUsersInRole(UsersInRoleVo usersInRoleVo) {
        List<String> users =  usersInRoleVo.getUsers();
        if (!CollectionUtils.isEmpty(users)) {
            for (String user : users) {
                SysUserRole r = new SysUserRole(user,usersInRoleVo.getRoleId());
                this.delete(new EntityWrapper<>(r));
            }
        }
        return true;
    }
}
