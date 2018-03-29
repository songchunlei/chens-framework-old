package com.chens.admin.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.chens.admin.service.ISysLogService;
import com.chens.admin.entity.SysLog;


import com.chens.core.web.BaseWebController;

/**
 *
 *  系统日志控制器
 *
 * @author chunlei.song@live.com
 * @create 2018-03-24
 */
@Controller
@RequestMapping("/sysLogController")
public class SysLogController extends BaseWebController<ISysLogService,SysLog> {


}
