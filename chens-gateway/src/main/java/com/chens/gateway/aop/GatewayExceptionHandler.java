package com.chens.gateway.aop;

import com.chens.core.aop.BaseControllerExceptionHandler;
import com.chens.core.exception.BaseException;
import com.chens.core.util.ResultHelper;
import com.chens.core.vo.Result;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常拦截
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/26
 */
@ControllerAdvice
public class GatewayExceptionHandler extends BaseControllerExceptionHandler {
    /**
     * 拦截请求异常
     */
    @ExceptionHandler(ZuulException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleBaseException(ZuulException e) {
        log.error("请求异常:", e);
        return ResultHelper.getError(e.nStatusCode, e.errorCause);
    }
}
