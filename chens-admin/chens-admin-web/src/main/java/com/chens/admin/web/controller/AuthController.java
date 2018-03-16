package com.chens.admin.web.controller;

import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chens.admin.service.IAuthService;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;


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
        boolean validateFlg = authService.Validate(authRequest);
        return doSuccess(validateFlg);
    }

    @RequestMapping("/loginout")
    public ResponseEntity<Result> loginout() {
            return doSuccess("退出成功");
    }


    @PostMapping("/findByUserNameAndPassword")
    @ResponseBody
    public SysUser findByUserNameAndPassword(@RequestBody AuthRequest authRequest) {
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
        return authService.findByUsernameAndPassword(authRequest);
    }

}
