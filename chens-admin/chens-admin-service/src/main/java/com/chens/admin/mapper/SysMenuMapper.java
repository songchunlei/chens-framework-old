package com.chens.admin.mapper;

import com.chens.admin.entity.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 用户对应菜单
     * @param userId
     * @return
     */
    List<SysMenu> getMenuListByUserId(String userId);
}
