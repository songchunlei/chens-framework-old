package com.chens.admin.service.impl;

import com.chens.core.entity.SysMenu;
import com.chens.admin.mapper.SysMenuMapper;
import com.chens.admin.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.vo.MenuTree;
import com.chens.core.vo.ZTree;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<ZTree> tree() {
        return baseMapper.tree();
    }

    @Override
    public List<SysMenu> getMenuListByUserId(Long userId) {
        return baseMapper.getMenuListByUserId(userId);
    }

    @Override
    public List<MenuTree> getMenuTreeListByUserId(Long userId) {
        List<MenuTree> trees = new ArrayList<>();
        List<SysMenu> sysMenus =  baseMapper.getMenuListByUserId(userId);
        if(!CollectionUtils.isEmpty(sysMenus))
        {
            for (SysMenu menu:sysMenus) {
                MenuTree menuTree = new MenuTree();
                menuTree.getMenu(menu);
                trees.add(menuTree);
            }
        }
        return trees;
    }
}
