package com.chens.admin.web.controller;


import com.chens.admin.service.ISysTenantService;
import com.chens.admin.entity.SysTenant;
import com.chens.core.web.BaseWebController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;


/**
 * <p>
 *  租户管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/tenantController")
public class SysTenantController extends BaseWebController<ISysTenantService,SysTenant> {


}

