package com.chens.admin.service.impl;


import com.chens.admin.service.ISysMenuService;
import com.chens.auth.client.feign.ISysTokenClient;
import com.chens.auth.vo.IJwtInfo;
import com.chens.auth.vo.UserInfo;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.entity.SysMenu;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.core.util.TreeUtil;
import com.chens.auth.client.vo.JWTToken;
import com.chens.core.vo.MenuTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.chens.admin.service.IAuthService;
import com.chens.admin.service.ISysRoleService;
import com.chens.admin.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.vo.sys.AuthRequest;
import org.springframework.transaction.annotation.Transactional;
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

   //protected Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private ISysTokenClient sysTokenClient;

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
    @Transactional
    public JWTToken login(AuthRequest authRequest) throws Exception {
        if(authRequest!=null) {
            //logger.info("*******AuthService.login****************");
            SysUser sysUser = this.findByUsernameAndPassword(authRequest);
            if(sysUser==null)
            {
                throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
            }
            return this.parseToken(sysTokenClient.createTokenByUser(sysUser));
        }
        throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
    }

    @Override
    @Transactional
    public boolean logout() {
        //logger.info("*******AuthService.loginout:****************"+token);
        String token = BaseContextHandler.getToken();
        if(StringUtils.isNotEmpty(token))
        {
            sysTokenClient.invalidToken(token);
        }
        return true;
    }

    @Override
    public JWTToken parseToken(String token) throws Exception {
        //解析jwtInfo
        UserInfo userInfo = sysTokenClient.parseToken(token);
        return this.parseToken(userInfo);
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

    private JWTToken parseToken(UserInfo userInfo) throws Exception {

        //logger.info("*******AuthService.parseToken****************");

        //获取菜单列表
        List<SysMenu> sysMenus = sysMenuService.getMenuListByUserId(userInfo.getId());
        //全量打平菜单树
        Map<String, MenuTree> all = new HashMap<String, MenuTree>();
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
        return new JWTToken(userInfo.getToken(), menus, all,userInfo );
    }

}
