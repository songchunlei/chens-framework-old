package com.chens.admin.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.chens.admin.service.ISysDictService;
import com.chens.core.entity.SysDict;


import com.chens.core.web.BaseWebController;

/**
 *
 *  字典值
 *
 * @author chunlei.song@live.com
 * @create 2018-03-23
 */
@Controller
@RequestMapping("/dictController")
public class SysDictController extends BaseWebController<ISysDictService,SysDict> {


}
