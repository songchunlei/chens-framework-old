package com.chens.auth.client.interceptor;

import com.chens.auth.client.annotation.IgnoreClientToken;
import com.chens.auth.client.feign.ISysTokenClient;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限拦截器
 * 引用自 https://gitee.com/geek_qi/ace-security,按现有框架做了调整
 * @auther songchunlei@qq.com
 * @create 2018/3/21
 */
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private ISysTokenClient sysTokenClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行服务拦截
        IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
        if(annotation==null)
        {
            annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }

        String token = request.getHeader(sysTokenClient.getUserHeaderKey());
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(sysTokenClient.getUserHeaderKey())) {
                        token = cookie.getValue();
                    }
                }
            }
        }

        if(StringUtils.isEmpty(token))
        {
            throw new BaseException(BaseExceptionEnum.TOKEN_ERROR);
        }

        //解析token
        UserInfo userInfo = sysTokenClient.parseToken(token);

        //存入缓存
        BaseContextHandler.setUserName(userInfo.getUsername());
        BaseContextHandler.setName(userInfo.getName());
        BaseContextHandler.setUserId(userInfo.getId());
        BaseContextHandler.setTenantId(userInfo.getTenantId());
        BaseContextHandler.setToken(token);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
