package com.chens.admin.web.service;

import com.chens.core.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据用户id查询角色
     * @return
     */
    List<SysRole> findRoleListByUserId(Long userId);
}
