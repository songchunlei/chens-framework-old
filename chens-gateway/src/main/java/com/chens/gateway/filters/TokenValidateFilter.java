package com.chens.gateway.filters;

import com.chens.admin.entity.SysLog;
import com.chens.admin.remote.ISysLogClient;
import com.chens.admin.util.DBLog;
import com.chens.auth.client.feign.ISysTokenClient;
import com.chens.auth.client.service.IAuthClientService;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.BaseException;
import com.chens.core.util.ClientUtil;
import com.chens.core.vo.UserInfo;
import com.chens.core.exception.BaseExceptionEnum;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Token鉴权
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/22
 */
@Component
public class TokenValidateFilter extends ZuulFilter {

    @Autowired
    private ISysLogClient sysLogClient;

    @Autowired
    private IAuthClientService authClientService;

    @Override
    public String filterType() {
        // pre 在发起请求之前会执行这个filter
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 这个是执行顺序，因为同一个类型的filter可能有多个。 值越小越靠前
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {

       // zuul中，将当前请求的上下文信息存在线程变量中。取出来
        RequestContext ctx = RequestContext.getCurrentContext();

        // 从上下文中获取httprequest对象
        HttpServletRequest request = ctx.getRequest();

        //获取用户
        UserInfo userInfo = this.GetUserInfoByToken(ctx,request);

        //处理权限(待完成)
        checkUserPermission(ctx,userInfo);

        //记录日志
        writeToLog(ctx,userInfo);


        return null;
    }


    /**
     * 处理用户信息并放入ZuulRequestHeader
     * @param ctx
     * @param request
     * @return
     */
    private UserInfo GetUserInfoByToken(RequestContext ctx,HttpServletRequest request)
    {

        // 检验token是否正确
        UserInfo userInfo = authClientService.getUserInfo(request);
        if (userInfo == null) {
            forbidden();
            return null;
        }

        //放入请求串
        ctx.addZuulRequestHeader(authClientService.getUserHeaderKey(), userInfo.getToken());

        return userInfo;
    }

    /**
     * 处理权限
     * @param ctx
     * @param userInfo
     */
    private void checkUserPermission(RequestContext ctx,UserInfo userInfo)
    {

    }

    /**
     * 记录操作日志
     * @param ctx
     * @param userInfo
     */
    private void writeToLog(RequestContext ctx,UserInfo userInfo)
    {
        String host = ClientUtil.getClientIp(ctx.getRequest());
        Date now = new Date();
        SysLog sysLog = new SysLog("opt", host, "url",now,userInfo.getId(),now,userInfo.getId(),userInfo.getTenantId());
        DBLog.getInstance().setSysLogClient(sysLogClient).offerQueue(sysLog);
    }

    // 设置response的状态码为403
    private void forbidden() {
        // zuul中，将请求附带的信息存在线程变量中。
        RequestContext.getCurrentContext().setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        ReflectionUtils.rethrowRuntimeException(new BaseException(BaseExceptionEnum.TOKEN_ERROR));
    }

}
