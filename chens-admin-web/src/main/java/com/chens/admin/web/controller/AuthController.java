package com.chens.admin.web.controller;

import com.chens.core.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chens.admin.web.service.IAuthService;
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

    @RequestMapping("/login")
    public ResponseEntity<Result> login(@RequestBody AuthRequest authRequest) {
        boolean validateFlg = authService.Validate(authRequest);
        if (validateFlg)
        {
            return doSuccess("登录成功");
        }
        else
        {
            return doSuccess("成功");
        }
    }

    @RequestMapping("/loginout")
    public ResponseEntity<Result> loginout() {
            return doSuccess("退出成功");
    }


    @PostMapping("/findByUserNameAndPassword")
    @ResponseBody
    public SysUser findByUserNameAndPassword(@RequestBody AuthRequest authRequest) {
        return authService.findByUsernameAndPassword(authRequest);
    }

}
