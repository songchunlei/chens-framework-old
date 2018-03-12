package com.chens.admin.web.controller;

import com.chens.admin.web.service.ISysMenuService;
import com.chens.core.entity.SysMenu;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单管理页面
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/9
 */
@Controller
@RequestMapping("/menu")
public class SysMenuController extends BaseWebController<ISysMenuService,SysMenu>{

    /**
     * 菜单树
     * @return
     */
    @RequestMapping("/tree")
    public ResponseEntity<Result> tree() {
        return doSuccess(service.tree());
    }

}
