package com.chens.admin.web.controller;



import com.chens.admin.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.web.BaseWebController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;


/**
 * <p>
 *  用户管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/userController")
public class SysUserController extends BaseWebController<ISysUserService,SysUser> {

}

