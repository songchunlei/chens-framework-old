package com.chens.admin.service;

import com.chens.core.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 *
 *  用户-角色服务接口
 *
 * @author chunlei.song@live.com
 * @create 2018-03-17
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 增加用户
     * @param roleId
     * @param userIds
     * @return
     */
    boolean AddUsersInRole(String roleId,String userIds);

    /**
     * 删除用户
     * @param roleId
     * @param userIds
     * @return
     */
    boolean DeleteUsersInRole(String roleId,String userIds);
	
}
