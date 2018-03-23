package com.chens.admin.service.impl;

import com.chens.auth.client.util.UserAuthUtil;
import com.chens.core.constants.CommonConstants;
import com.chens.core.entity.SysMenu;
import com.chens.admin.mapper.SysMenuMapper;
import com.chens.admin.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.entity.SysUser;
import com.chens.core.util.TreeUtil;
import com.chens.core.vo.JWTToken;
import com.chens.core.vo.MenuTree;
import com.chens.core.vo.ZTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserAuthUtil userAuthUtil;

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
        List<SysMenu> sysMenus =  this.getMenuListByUserId(userId);
        if(!CollectionUtils.isEmpty(sysMenus))
        {
            for (SysMenu menu:sysMenus) {
                MenuTree menuTree;
                menuTree = new MenuTree();
                menuTree.getMenu(menu);
                trees.add(menuTree);
            }
        }
        trees = TreeUtil.buildByRecursive(trees, CommonConstants.BASE_TREE_ROOT);
        return trees;
    }

    @Override
    public JWTToken getMenuTreeMapListByUserId(SysUser sysUser) {

        //获取菜单列表
        List<SysMenu> sysMenus =  this.getMenuListByUserId(sysUser.getId());

        //全量打平菜单树
        Map<Long, MenuTree > all = new HashMap<Long, MenuTree>();

        //菜单嵌套树
        List<MenuTree> trees = new ArrayList<>();

        if(!CollectionUtils.isEmpty(sysMenus))
        {
            for (SysMenu menu:sysMenus) {
                MenuTree menuTree = new MenuTree();
                menuTree.getMenu(menu);
                trees.add(menuTree);
                all.put(menu.getId(),menuTree.clone());
            }
        }

        List<MenuTree> menus = TreeUtil.buildByRecursive(trees, CommonConstants.BASE_TREE_ROOT);

        return new JWTToken(userAuthUtil.createToken(sysUser),menus,all);

    }


}
