package com.chens.admin.web.controller;



import com.baomidou.mybatisplus.plugins.Page;
import com.chens.admin.service.ISysUserService;
import com.chens.admin.entity.SysUser;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;


/**
 * <p>
 *  用户管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/userController")
public class SysUserController extends BaseWebController<ISysUserService,SysUser> {


    /**
     * 创建用户
     * @param sysUser
     * @return
     */
    @PostMapping("createUser")
    public ResponseEntity<Result> createUser(@RequestBody @Validated SysUser sysUser) {
        if(sysUser!=null)
        {
            return doSuccess(CommonConstants.SAVE_SUCCESS,service.createAccount(sysUser));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 重置密码
     * @param userId 用户id
     * @param isRandom 是否随机
     * @return
     */
    @PutMapping("/restPwd")
    public ResponseEntity<Result> restPwd(@RequestParam("userId") String userId, @RequestParam("isRandom") boolean isRandom) {
        return doSuccess(CommonConstants.SAVE_SUCCESS,service.restPwd(userId,isRandom));
    }

    /**
     * 根据角色id获取角色下的用户
     * @param spage
     * @return
     */
    @PostMapping("/getUserListByRoleId")
    public ResponseEntity<Result> getUserListByRoleId(@RequestBody QueryPageEntity<SysUser> spage) {
        Page<SysUser> page = this.createPage(spage);
        if(page!=null && spage.getSearch()!=null) {
            return doSuccess(CommonConstants.QUERY_SUCCESS,service.getUserListByRoleId(page,spage.getSearch()));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 根据租户id获取角色下的用户
     * @param spage
     * @return
     */
    @PostMapping("/getUserListByTenantId")
    public ResponseEntity<Result> getUserListByTenantId(@RequestBody QueryPageEntity<SysUser> spage) {
        Page<SysUser> page = this.createPage(spage);
        if(page!=null && spage.getSearch()!=null) {
            return doSuccess(CommonConstants.QUERY_SUCCESS,service.getUserListByTenantId(page,spage.getSearch()));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

}

