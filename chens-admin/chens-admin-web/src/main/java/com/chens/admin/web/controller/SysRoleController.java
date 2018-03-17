package com.chens.admin.web.controller;


import com.chens.admin.service.ISysRoleService;
import com.chens.core.entity.SysRole;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.vo.sys.RolesByUserId;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class SysRoleController extends BaseWebController<ISysRoleService,SysRole> {


    @GetMapping("/getRoleListByUserId")
    public ResponseEntity<Result> getRoleListByUserId(Long userId) {
        if(userId!=null){
            return doSuccess(service.findRoleListByUserId(userId));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    @PostMapping("/saveUserRoleList")
    public ResponseEntity<Result> saveUserRoleList(RolesByUserId rolesByUserId) {
        if(rolesByUserId!=null){
            return doSuccess(service.saveUserRoleList(rolesByUserId));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

}

