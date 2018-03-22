package com.chens.admin.web.controller;

import com.chens.admin.service.*;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.sys.RegisterTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chens.core.vo.sys.AuthRequest;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;


/**
 * 权限控制
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/5
 */
@Controller
@RequestMapping("/authController")
public class AuthController extends BaseController{

    @Autowired
    private IAuthService authService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysTenantService sysTenantService;

    @Autowired
    private ISysMenuService sysMenuService;

    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody AuthRequest authRequest) {
        if(authRequest==null){
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(authRequest.getUserName()))
        {
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_NO_USERNAME);
        }
        if(StringUtils.isEmpty(authRequest.getPassword()))
        {
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_NO_PASSWORD);
        }
        SysUser sysUser = authService.findByUsernameAndPassword(authRequest);
        if(sysUser!=null)
        {
            return doSuccess(sysMenuService.getMenuTreeMapListByUserId(sysUser));
        }
        throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
    }

    @RequestMapping("/logout")
    public ResponseEntity<Result> loginout() {
        return doSuccess("退出成功");
    }


    /**
     * 注册用户
     * @param sysUser
     * @return
     */
    @PostMapping("registerUser")
    public ResponseEntity<Result> registerUser(@RequestBody @Validated SysUser sysUser) {
        if(sysUser!=null)
        {
            if(StringUtils.isEmpty(sysUser.getPassword()))
            {
                throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_NO_PASSWORD);
            }
            return doSuccess("保存成功",sysUserService.createAccount(sysUser));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 注册租户
     * @param registerTenant
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Result> register(@RequestBody @Validated RegisterTenant registerTenant) {
        if(registerTenant!=null)
        {

            return doSuccess("保存成功",sysTenantService.register(registerTenant));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }



    /**
     * 解析token，测试
     * @param token
     * @return
     */
    /*
    @RequestMapping("/token/parse")
    public ResponseEntity<Result> parseToken(String token) throws Exception {
        return doSuccess(jwtTokenProvider.parseToken(token)) ;
    }
    */

}
