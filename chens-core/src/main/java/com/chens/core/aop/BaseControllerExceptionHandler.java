package com.chens.core.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.chens.core.exception.AuthException;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.TimeOutException;
import com.chens.core.handler.MyExceptionHandler;
import com.chens.core.util.AopTargetUtil;
import com.chens.core.vo.Result;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import feign.FeignException;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午3:19:56
 */
public class BaseControllerExceptionHandler{

    private Class<?> clazz;
    protected Logger log;

    protected BaseControllerExceptionHandler() {
        clazz = AopTargetUtil.getSuperClassGenricType(getClass(), 0);
        log = LoggerFactory.getLogger(clazz);
    }

    /**
     * 拦截超时异常
     */
    @ExceptionHandler(TimeOutException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ResponseBody
    public Result handleBaseException(TimeOutException e) {
        log.error("超时异常:", e);
        return MyExceptionHandler.getErrorResult(e);
    }

    /**
     * 拦截授权异常
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result handleBaseException(AuthException e) {
        log.error("授权异常:", e);
        return MyExceptionHandler.getErrorResult(e);
    }

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleBaseException(BaseException e) {
        log.error("业务异常:", e);
        return MyExceptionHandler.getErrorResult(e);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleRuntimeException(RuntimeException e) {
        log.error("运行时异常:", e);
        return MyExceptionHandler.getErrorResult(e);
    }

    /**
     * 必填报错拦截
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleBindException(MethodArgumentNotValidException e) {
        log.error("必填报错异常:", e);
        return MyExceptionHandler.getErrorResult(e);
    }

    /*
    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleRuntimeException(HystrixRuntimeException e) {
        log.error("Hystrix运行时异常:", e);
        return MyExceptionHandler.getErrorResult(e);
    }


    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleFeignException(FeignException e) {
        log.error("Feign异常:"+e.getMessage());
        return MyExceptionHandler.getErrorResult(e);
    }
    */
    
}
