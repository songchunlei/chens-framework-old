package com.chens.admin.service.impl;

import com.chens.admin.entity.SysMenu;
import com.chens.admin.enums.SysMenuEnum;
import com.chens.admin.service.ISysMenuService;
import com.chens.admin.service.IUserTokenVoService;
import com.chens.admin.vo.MenuTree;
import com.chens.admin.vo.UserTokenVo;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.TreeUtil;
import com.chens.core.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author songchunlei@qq.com
 * @create 2018/8/28
 */
public class UserTokenVoServiceImpl implements IUserTokenVoService {
    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public UserTokenVo getUserTokenVo(UserInfo userInfo) {

        if(userInfo==null){
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_NO_USERNAME);
        }

        //获取菜单列表
        List<SysMenu> sysMenus = sysMenuService.getMenuListByUserId(userInfo.getId());
        //全量打平菜单树
        Map<String, MenuTree> all = new HashMap<String, MenuTree>();
        //菜单嵌套树
        List<MenuTree> trees = new ArrayList<>();
        //循环
        if (!CollectionUtils.isEmpty(sysMenus)) {
            for (SysMenu menu : sysMenus) {
                MenuTree menuTree = new MenuTree(menu);
                trees.add(menuTree);
                //当菜单类型为页面时，放入子菜单（不克隆）
                if (SysMenuEnum.PAGE.getCode().equals(menu.getType())) {
                    all.put(menu.getCode(), menuTree);
                } else {
                    all.put(menu.getCode(), menuTree.clone());
                }

            }
        }
        //构建树结构
        List<MenuTree> menus = TreeUtil.build(trees, CommonConstants.BASE_TREE_ROOT);
        //返回JWTToken
        return new UserTokenVo(userInfo.getToken(), menus, all, userInfo);
    }
}
