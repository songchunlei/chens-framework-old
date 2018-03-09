package com.chens.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chens.admin.web.service.IAuthService;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;


/**
 * 权限控制
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/5
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController{

    @Autowired
    private IAuthService authService;

    @RequestMapping("/login")
    public ResponseEntity<Result> login(AuthRequest authRequest) {
        boolean validateFlg = authService.Validate(authRequest);
        if (validateFlg)
        {
            return doSuccess("登录成功");
        }
        else
        {
            //throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_ERROR);
            return doSuccess("成功");
        }
    }

    @RequestMapping("/loginout")
    public ResponseEntity<Result> loginout() {
            return doSuccess("退出成功");
    }



}
