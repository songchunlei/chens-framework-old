package com.chens.admin.service;

import com.chens.admin.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;
import com.chens.admin.vo.QueryRolesByUserId;

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
     * @param queryRolesByUserId
     * @return
     */
    boolean saveUserRoleListByUserId(QueryRolesByUserId queryRolesByUserId);

    /**
     * 增加用户
     * @param roleId
     * @param userIds
     * @return
     */
    boolean addUsersInRole(String roleId,String userIds);

    /**
     * 删除用户
     * @param roleId
     * @param userIds
     * @return
     */
    boolean deleteUsersInRole(String roleId,String userIds);
	
}
