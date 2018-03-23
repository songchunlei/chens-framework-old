package com.chens.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.chens.core.web.BaseController;
import com.chens.admin.service.ISysDictTypeService;
import com.chens.core.entity.SysDictType;


import com.chens.core.web.BaseWebController;

/**
 *
 *  字典类型
 *
 * @author chunlei.song@live.com
 * @create 2018-03-23
 */
@Controller
@RequestMapping("/dictTypeController")
public class SysDictTypeController extends BaseWebController<ISysDictTypeService,SysDictType> {


}
