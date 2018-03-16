package com.chens.admin.web.controller;


import com.chens.admin.service.ISysPermissionService;
import com.chens.core.entity.SysPermission;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  权限管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/permissionController")
public class SysPermissionController extends BaseWebController<ISysPermissionService,SysPermission> {

    /**
     * 菜单树
     * @return
     */
    @RequestMapping("/tree")
    public ResponseEntity<Result> tree() {
        return doSuccess(service.tree());
    }

}

