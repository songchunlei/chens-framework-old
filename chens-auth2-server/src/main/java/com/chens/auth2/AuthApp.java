package com.chens.auth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 授权启动
 *
 * @auther songchunlei@qq.com
 * @create 2018/2/12
 */
@SpringBootApplication
@EnableAuthorizationServer
public class AuthApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }

    /**
     *
     *
     AuthorizationEndpoint 根据用户认证获得授权码，有下面两个方法：
     /oauth/authorize - GET
     /oauth/authorize - POST

     TokenEndpoint 客户端根据授权码获取 token
     /oauth/token - GET
     /oauth/token - POST

     CheckTokenEndpoint 可以用于远程解码令牌
     /oauth/check_token

     WhitelabelApprovalEndpoint 显示授权服务器的确认页。
     /oauth/confirm_access

     WhitelabelErrorEndpoint 显示授权服务器的错误页
     /oauth/error
     */
}
