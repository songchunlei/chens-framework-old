package com.chens.auth.client.interceptor;

import com.chens.auth.client.annotation.IgnoreUserToken;
import com.chens.auth.client.service.IAuthClientService;
import com.chens.auth.constants.AuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限拦截器
 * 引用自 https://gitee.com/geek_qi/ace-security,按现有框架做了调整
 * @author songchunlei@qq.com
 * @create 2018/3/21
 */
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter{

    /**
     * 简单校验场景
     * 是否使用request里的userKey(用户id)校验
     */
    private boolean isSimpleValidate = false;

    public UserAuthRestInterceptor() {
    }

    public UserAuthRestInterceptor(boolean isSimpleValidate) {
        this.isSimpleValidate = isSimpleValidate;
    }

    @Autowired
    private IAuthClientService authClientService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行服务拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if(annotation==null)
        {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }

        if(isSimpleValidate)
        {
            //简单校验
            authClientService.getUserInfoBySimple(request);
        }
        else
        {
            //引用授权服务
            authClientService.getUserInfo(request);
        }


        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
