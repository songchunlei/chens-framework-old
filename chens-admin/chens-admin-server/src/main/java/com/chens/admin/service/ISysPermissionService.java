package com.chens.admin.service;

import com.chens.core.entity.SysPermission;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.vo.ZTree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 根据用户id查询角色
     * @return
     */
    List<SysPermission> findPermissionListByRoleId(Long roleId);

    /**
     * 树
     * @return
     */
    List<ZTree> tree();
}
