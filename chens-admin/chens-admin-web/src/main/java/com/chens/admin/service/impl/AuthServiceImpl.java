package com.chens.admin.service.impl;


import com.chens.admin.service.ISysMenuService;
import com.chens.auth.client.jwt.IJwtInfo;
import com.chens.auth.client.util.UserAuthUtil;
import com.chens.core.constants.CommonConstants;
import com.chens.core.entity.SysMenu;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.TreeUtil;
import com.chens.auth.client.vo.JWTToken;
import com.chens.core.vo.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.chens.admin.service.IAuthService;
import com.chens.admin.service.ISysRoleService;
import com.chens.admin.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.vo.sys.AuthRequest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限控制实现
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/4
 */
@Service
public class AuthServiceImpl implements IAuthService{


    @Autowired
    private UserAuthUtil userAuthUtil;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public SysUser findByUsernameAndPassword(AuthRequest authRequest) throws BaseException{
        SysUser sysUser = sysUserService.findByUsername(authRequest);

        /* 登录先不查询角色
        if(sysUser!=null)
        {
            sysUser.setRoles(sysRoleService.getRoleListByUserId(sysUser.getId()));
        }
        */

        return sysUser;
    }

    @Override
    public JWTToken login(AuthRequest authRequest) throws Exception {
        SysUser sysUser = this.findByUsernameAndPassword(authRequest);
        if(sysUser!=null) {
            return this.parseToken(userAuthUtil.createToken(sysUser));
        }
        throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
    }

    @Override
    public JWTToken parseToken(String token) throws Exception {
        //解析jwtInfo
        IJwtInfo jwtInfo = userAuthUtil.getUserInfo(token);
        //获取菜单列表
        List<SysMenu> sysMenus = sysMenuService.getMenuListByUserId(jwtInfo.getId());
        //全量打平菜单树
        Map<Long, MenuTree> all = new HashMap<Long, MenuTree>();
        //菜单嵌套树
        List<MenuTree> trees = new ArrayList<>();
        //循环
        if (!CollectionUtils.isEmpty(sysMenus)) {
            for (SysMenu menu : sysMenus) {
                MenuTree menuTree = new MenuTree();
                menuTree.getMenu(menu);
                trees.add(menuTree);
                all.put(menu.getId(), menuTree.clone());
            }
        }
        //构建树结构
        List<MenuTree> menus = TreeUtil.buildByRecursive(trees, CommonConstants.BASE_TREE_ROOT);
        //返回JWTToken
        return new JWTToken(token, menus, all,jwtInfo );
    }


    @Override
    public boolean Validate(AuthRequest authRequest){
        SysUser sysUser = sysUserService.findByUsername(authRequest);
        if(sysUser!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
