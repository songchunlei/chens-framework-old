package com.chens.auth.controller;

import com.chens.auth.service.ISysTokenService;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.vo.AuthRequest;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 授权服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/29
 */
@Controller
@RequestMapping("/AuthController")
public class AuthController extends BaseController{

    @Autowired
    private ISysTokenService sysTokenService;

    /**
     * 创建token-根据登录请求(依赖admin-web)
     * @param authRequest
     * @return
     */
    @PostMapping("/getToken")
    public ResponseEntity<Result> createTokenByAuthRequest(@RequestBody AuthRequest authRequest) {
        return doSuccess(sysTokenService.createToken(authRequest));
    }

    /**
     * 注销token
     * @return
     */
    @DeleteMapping("/invalidToken")
    public ResponseEntity<Result> invalidToken() {
        return doSuccess(sysTokenService.invalidToken(BaseContextHandler.getToken()));
    }

}
