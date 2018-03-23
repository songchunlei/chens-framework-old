package com.chens.admin.service;

import com.chens.core.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.entity.SysUser;
import com.chens.core.vo.JWTToken;
import com.chens.core.vo.MenuTree;
import com.chens.core.vo.ZTree;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 获取全量菜单树
     * @return
     */
    List<ZTree> tree();

    /**
     * 根据用户id获取对应菜单(权限)
     * @param userId
     * @return
     */
    List<SysMenu> getMenuListByUserId(Long userId);

    /**
     * 根据用户id获取对应菜单(权限)树
     * @param userId
     * @return
     */
    List<MenuTree> getMenuTreeListByUserId(Long userId);

    /**
     * 根据用户id获取对应菜单(权限)树Map
     * @param sysUser
     * @return
     */
    JWTToken getMenuTreeMapListByUserId(SysUser sysUser);
}
