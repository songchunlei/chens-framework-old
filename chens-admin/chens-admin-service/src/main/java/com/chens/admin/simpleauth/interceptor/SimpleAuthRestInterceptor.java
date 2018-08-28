package com.chens.admin.simpleauth.interceptor;

import com.chens.admin.simpleauth.annotation.IgnoreUserPwd;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 简单的用户权限拦截器
 * @author songchunlei@qq.com
 * @create 2018/8/28
 */
public class SimpleAuthRestInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行密码校验拦截
        IgnoreUserPwd annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserPwd.class);
        if(annotation==null)
        {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserPwd.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }
        //判断用户名是否为空，为空则抛异常
        if(StringUtils.isNotEmpty(BaseContextHandler.getUserName())){
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_SIMPLE_ERROR);
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
