package com.chens.admin.web.controller;

import com.chens.admin.service.ISysMenuService;
import com.chens.admin.entity.SysMenu;
import com.chens.core.constants.CommonConstants;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;

/**
 * 菜单管理页面
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/9
 */
@Controller
@RequestMapping("/menuController")
public class SysMenuController extends BaseWebController<ISysMenuService,SysMenu>{

    /**
     * 菜单树
     * @return
     */
    @RequestMapping("/tree")
    public ResponseEntity<Result> tree() {
        return doSuccess(CommonConstants.QUERY_SUCCESS,service.tree());
    }

    /**
     * 根据用户id获取菜单
     * @param userId
     * @return
     */
    @GetMapping("/getMenuList/{userId}")
    public ResponseEntity<Result> getMenuListByUserId(@PathVariable @NotNull String userId) {
        return doSuccess(CommonConstants.QUERY_SUCCESS,service.getMenuTreeListByUserId(userId));
    }

    /**
     * 根据用户id获取菜单
     * @return
     */
    @GetMapping("/getAllMenuTreeList")
    public ResponseEntity<Result> getMenuListByUserId() {
        return doSuccess(CommonConstants.QUERY_SUCCESS,service.getAllMenuTreeList());
    }


}
