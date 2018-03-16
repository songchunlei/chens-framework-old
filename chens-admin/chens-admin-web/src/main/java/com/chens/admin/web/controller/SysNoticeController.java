package com.chens.admin.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.chens.admin.service.ISysNoticeService;
import com.chens.core.entity.SysNotice;


import com.chens.core.web.BaseWebController;

/**
 *
 *  控制器
 *
 * @author chunlei.song@live.com
 * @create 2018-03-12
 */
@Controller
@RequestMapping("/noticeController")
public class SysNoticeController extends BaseWebController<ISysNoticeService,SysNotice> {


}
