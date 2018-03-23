package com.chens.admin.service;

import com.chens.core.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 *
 *  服务接口
 *
 * @author chunlei.song@live.com
 * @create 2018-03-17
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    boolean AddUsersInRole(Long roleId,String users);
	
}
