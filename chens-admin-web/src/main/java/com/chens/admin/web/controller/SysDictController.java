package com.chens.admin.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chens.core.constants.CommonContants;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.PageVo;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.chens.core.web.BaseController;
import com.chens.admin.web.service.ISysDictService;
import com.chens.core.entity.SysDict;


import com.chens.core.web.BaseWebController;

import java.lang.reflect.Field;

/**
 *
 *  控制器
 *
 * @author chunlei.song@live.com
 * @create 2018-03-12
 */
@Controller
@RequestMapping("/dict")
public class SysDictController extends BaseWebController<ISysDictService,SysDict> {

    @PostMapping("/test")
    public ResponseEntity<Result> test(SysDict sysDict){
        QueryPageEntity<SysDict> query =  new QueryPageEntity<SysDict>();
        PageVo page = new PageVo();
        query.setPage(page);
        SysDict sysDict1 = new SysDict();
        query.setSearch(sysDict1);
        return  doSuccess(query);
    }
}
