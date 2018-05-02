package com.chens.admin.web.controller;



import com.baomidou.mybatisplus.plugins.Page;
import com.chens.admin.service.ISysUserService;
import com.chens.admin.entity.SysUser;
import com.chens.admin.vo.RestPwd;
import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
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
    @Override
    @PostMapping("/create")
    public ResponseEntity<Result> create(@RequestBody @Validated(InsertValid.class) SysUser sysUser)  {
        if(sysUser!=null)
        {
            //业务创建用户要指定租户
            sysUser.setTenantId(BaseContextHandler.getTenantId());
            return super.create(sysUser);
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 重置密码
     * @param restPwd
     * @return
     */
    @PutMapping("/restPwd")
    public ResponseEntity<Result> restPwd(@RequestBody @Validated RestPwd restPwd) {
        return doSuccess(CommonConstants.SAVE_SUCCESS,service.restPwd(restPwd));
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

    @PostMapping("/pageListInTenant")
    public ResponseEntity<Result> pageListInTenant(@RequestBody QueryPageEntity<SysUser> spage) {
        if(spage!=null)
        {
            spage.getPage().setTenant(true);
            return super.pagelist(spage);
        }
        else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }

    }
}

