package com.chens.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.chens.core.web.BaseController;
import com.chens.auth.service.ISysTokenService;
import com.chens.auth.entity.SysToken;


import com.chens.core.web.BaseWebController;

/**
 *
 *  控制器
 *
 * @author chunlei.song@live.com
 * @create 2018-03-25
 */
@Controller
@RequestMapping("/sysTokenController")
public class SysTokenController extends BaseWebController<ISysTokenService,SysToken> {


}
