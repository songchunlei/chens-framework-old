package com.chens.core.handler;

import com.alibaba.fastjson.JSONObject;
import com.chens.core.exception.AuthException;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.exception.TimeOutException;
import com.chens.core.util.ResultHelper;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.Result;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 常用exception拦截
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/31
 */
public class MyExceptionHandler {

    private static final String JSON_CODE = "code";
    private static final String JSON_MSG = "msg";

    /**
     * 获取错误串
     * @param throwable
     * @return
     */
    public static String getErrorResponseBody(Throwable throwable)
    {
        //直接调用实体类方法
        return getErrorResult(throwable).toString();
    }

    public static Result getErrorResult(Throwable throwable)
    {
        /**
         * Token超时异常
         */
        if(throwable instanceof TimeOutException)
        {
            TimeOutException exception = (TimeOutException) throwable;
            return ResultHelper.getError(BaseExceptionEnum.TOKEN_EXPIRED);
        }
        /**
         * 授权异常
         */
        else if(throwable instanceof AuthException)
        {
            AuthException exception = (AuthException) throwable;
            return ResultHelper.getError(exception.getCode(), exception.getMessage());
        }
        /**
         * 基础异常提示
         */
        else if(throwable instanceof BaseException)
        {
            BaseException exception = (BaseException) throwable;
            return ResultHelper.getError(exception.getCode(), exception.getMessage());
        }
        /**
         * 校验规则抛错
         */
        else if(throwable instanceof MethodArgumentNotValidException)
        {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) throwable;
            String msg = handlerValidateMsg(exception.getBindingResult());
            if(StringUtils.isNotEmpty(msg))
            {
                return ResultHelper.getError(BaseExceptionEnum.VALIDATE_NOPASS.getCode(),msg);
            }
            return ResultHelper.getError(BaseExceptionEnum.SERVER_ERROR.getCode(), exception.getMessage());
        }
        /**
         * 请求超时
         */
        else if(throwable instanceof TimeoutException)
        {
            return ResultHelper.getError(BaseExceptionEnum.TIMEOUT);
        }
        /**
         * 运行时抛错
         */
        else if(throwable instanceof RuntimeException)
        {
            RuntimeException exception = (RuntimeException) throwable;
            return ResultHelper.getError(BaseExceptionEnum.SERVER_ERROR.getCode(), exception.getMessage());
        }
        else
        {
            return ResultHelper.getError(BaseExceptionEnum.SERVER_ERROR.getCode(), throwable.getMessage());
        }
    }

    /**
     * 获取校验密码提示
     * @param result
     * @return
     */
    public static String handlerValidateMsg(BindingResult result){
        String errMsg = "";

        if(result.hasErrors())
        {
            List<ObjectError> errors = result.getAllErrors();
            for(ObjectError error :errors)
            {
                String errMsgTemp = error.getDefaultMessage();
                errMsg = ("").equals(errMsg)?errMsgTemp:errMsg+","+errMsgTemp;
            }
        }
        return errMsg;
    }
}
