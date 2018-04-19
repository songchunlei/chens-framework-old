package com.chens.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.admin.entity.SysRoleMenu;
import com.chens.admin.service.ISysRoleMenuService;
import com.chens.admin.service.ISysUserRoleService;
import com.chens.admin.entity.SysRole;
import com.chens.admin.mapper.SysRoleMapper;
import com.chens.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.admin.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
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

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysRole> getRoleListByUserId(String userId) {
        return baseMapper.getRoleListByUserId(userId);
    }


    /**
     * 重构角色删除逻辑，连带删除
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Serializable id) {
        //1.删除用户-关联关系
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId((String)id);
        sysUserRoleService.delete(new EntityWrapper<>(sysUserRole));
        //2.删除角色-菜单关系
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId((String)id);
        sysRoleMenuService.delete(new EntityWrapper<>(sysRoleMenu));
        //3.删除角色
        return super.deleteById(id);
    }

}
