package com.chens.admin.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chens.admin.web.service.ISysTenantService;
import com.chens.core.entity.SysTenant;
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
 *  租户管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/tenant")
public class SysTenantController extends BaseController{

    @Autowired
    private ISysTenantService sysTenantService;

    @RequestMapping("/page")
    public ResponseEntity<Result> page(SysTenant sysTenant) {
        if(sysTenant != null ){
            Map<String,Object> resultMap = new HashMap<String,Object>();
            Page<SysTenant> tenantPage = this.createPage(request,10);
            resultMap.put("page",sysTenantService.selectPage(tenantPage,new EntityWrapper<SysTenant>(sysTenant)));
            return doSuccess(resultMap);
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }

    }

    @RequestMapping("/save")
    public ResponseEntity<Result> save(SysTenant sysTenant) {
        if(sysTenant != null){
            return doSuccess(sysTenantService.insertOrUpdate(sysTenant));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    @RequestMapping("/delete")
    public ResponseEntity<Result> delete(Long id) {
        if(id!=null){
            return doSuccess(sysTenantService.deleteById(id));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

}

