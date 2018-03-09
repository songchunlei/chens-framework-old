package com.chens.admin.web.controller;

import com.chens.admin.web.service.ISysMenuService;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysMenuController extends BaseController{
    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("/tree")
    public ResponseEntity<Result> tree() {
        return doSuccess(sysMenuService.tree());
    }
}
