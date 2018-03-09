package com.chens.admin.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chens.admin.web.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
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
public class SysUserController extends BaseController{

    @Autowired
    private ISysUserService sysUserService;




    @RequestMapping("/page")
    public ResponseEntity<Result> page(SysUser sysUser) {
        if(sysUser != null ){
            Map<String,Object> resultMap = new HashMap<String,Object>();
            Page<SysUser> userPage = this.createPage(request,10);
            resultMap.put("page",sysUserService.selectPage(userPage,new EntityWrapper<SysUser>(sysUser)));
            return doSuccess(resultMap);
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }

    }

    @RequestMapping("/save")
    public ResponseEntity<Result> save(SysUser sysUser) {
        if(sysUser != null){
             return doSuccess(sysUserService.insertOrUpdate(sysUser));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    @RequestMapping("/delete")
    public ResponseEntity<Result> delete(Long id) {
        if(id!=null){
            return doSuccess(sysUserService.deleteById(id));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }



}

