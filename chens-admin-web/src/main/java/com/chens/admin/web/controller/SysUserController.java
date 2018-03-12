package com.chens.admin.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chens.admin.web.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import com.chens.core.web.BaseWebController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  用户管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseWebController<ISysUserService,SysUser> {



}

