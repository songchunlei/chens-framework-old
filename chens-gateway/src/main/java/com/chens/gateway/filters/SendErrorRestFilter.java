package com.chens.gateway.filters;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * 请求错误过滤
 * 默认springcloud有一个errorfilter，会重定向到一个/error的路径
 * 如果要是自定义的errorfilter生效，关掉springcloud提供的这个errorfilter即可。
 * @auther songchunlei@qq.com
 * @create 2018/3/22
 */
@Component
public class SendErrorRestFilter extends SendErrorFilter{
    protected static final Logger logger = LoggerFactory.getLogger(SendErrorRestFilter.class);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        Throwable throwable = findCauseException(context.getThrowable());
        // 获取response状态码
        String status = String.valueOf(context.getResponseStatusCode());
        // 转成json格式输出
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", status);
        jsonObject.put("msg", throwable.getMessage());

        // 记录日志
        logger.warn("zuul后台有个异常", context.getThrowable());
        // 放进响应body里面
        context.setResponseBody(jsonObject.toJSONString());
        context.getResponse().setContentType("text/html;charset=UTF-8");
        // 处理了异常以后，就清空
        context.remove("throwable");
        return null;
    }

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    // 找出最初始的异常
    Throwable findCauseException(Throwable throwable) {
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        return throwable;
    }

}
