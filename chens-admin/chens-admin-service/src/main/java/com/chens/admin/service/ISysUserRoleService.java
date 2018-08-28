package com.chens.admin.service;

import com.chens.admin.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;
import com.chens.admin.vo.RolesInUserVo;
import com.chens.admin.vo.UsersInRoleVo;

/**
 *
 *  用户-角色服务接口
 *
 * @author chunlei.song@live.com
 * @create 2018-03-17
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    /**
     * 保存用户-角色关系
     * @param rolesInUserVo
     * @return
     */
    boolean addRolesInUser(RolesInUserVo rolesInUserVo);

    /**
     * 增加用户
     * @param usersInRoleVo
     * @return
     */
    boolean addUsersInRole(UsersInRoleVo usersInRoleVo);

    /**
     * 删除用户
     * @param usersInRoleVo
     * @return
     */
    boolean deleteUsersInRole(UsersInRoleVo usersInRoleVo);
	
}
