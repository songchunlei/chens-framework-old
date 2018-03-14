package com.chens.admin.web.controller;


import com.chens.admin.web.service.ISysRoleService;
import com.chens.core.entity.SysRole;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  角色管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/roleController")
public class SysRoleController extends BaseWebController<ISysRoleService,SysRole>{


    @RequestMapping("/getRoleListByUserId")
    public ResponseEntity<Result> delete(Long userId) {
        if(userId!=null){
            return doSuccess(service.findRoleListByUserId(userId));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

}

