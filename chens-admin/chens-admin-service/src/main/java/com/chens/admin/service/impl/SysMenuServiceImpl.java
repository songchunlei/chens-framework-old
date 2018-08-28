package com.chens.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.core.constants.CommonConstants;
import com.chens.admin.entity.SysMenu;
import com.chens.admin.mapper.SysMenuMapper;
import com.chens.admin.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.util.TreeUtil;
import com.chens.admin.vo.MenuTree;
import com.chens.core.vo.TreeVo;
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
    public List<SysMenu> getMenuListByUserId(String userId) {
        return baseMapper.getMenuListByUserId(userId);
    }

    @Override
    public List<MenuTree> getMenuTreeListByUserId(String userId) {
        List<SysMenu> sysMenus =  this.getMenuListByUserId(userId);
        List<MenuTree> trees = TreeUtil.build(MenuListToMenuTreeList(sysMenus), CommonConstants.BASE_TREE_ROOT);
        return trees;
    }

    @Override
    public List<MenuTree> getAllMenuTreeList() {
        List<SysMenu> sysMenus =  this.selectList(new EntityWrapper<>(new SysMenu()));
        List<MenuTree> trees = TreeUtil.build(MenuListToMenuTreeList(sysMenus), CommonConstants.BASE_TREE_ROOT);
        return trees;
    }

    /**
     * 树型转换
     * @param sysMenus
     * @return
     */
    private List<MenuTree> MenuListToMenuTreeList(List<SysMenu> sysMenus)
    {
        List<MenuTree> trees = new ArrayList<>();
        if(!CollectionUtils.isEmpty(sysMenus))
        {
            for (SysMenu menu:sysMenus) {
                trees.add(new MenuTree(menu));
            }
        }
        return trees;
    }


}
