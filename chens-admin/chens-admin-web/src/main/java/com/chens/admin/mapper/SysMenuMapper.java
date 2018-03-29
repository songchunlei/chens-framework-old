package com.chens.admin.mapper;

import com.chens.admin.entity.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chens.core.vo.ZTree;

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
     * 菜单树
     * @return
     */
    List<ZTree> tree();

    List<SysMenu> getMenuListByUserId(String userId);
}
