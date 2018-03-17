package com.chens.admin.web.controller;


import com.chens.admin.service.ISysTenantService;
import com.chens.core.entity.SysTenant;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.sys.RegisterTenant;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    /**
     * 注册租户
     * @param registerTenant
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Result> register(@RequestBody @Validated RegisterTenant registerTenant) {
        if(registerTenant!=null)
        {
            return doSuccess("保存成功",service.register(registerTenant));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

}

