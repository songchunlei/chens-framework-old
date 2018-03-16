package com.chens.core.aop;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.ResultHelper;
import com.chens.core.vo.Result;

import feign.FeignException;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午3:19:56
 */
public class BaseControllerExceptionHandler{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleBaseException(BaseException e) {
        log.error("业务异常:", e);

        return ResultHelper.getError(e.getCode(), e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleRuntimeException(RuntimeException e) {
        log.error("运行时异常:", e);
        return ResultHelper.getError(BaseExceptionEnum.SERVER_ERROR.getCode(),e.getMessage());
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleRuntimeException(HystrixRuntimeException e) {
        log.error("Hystrix运行时异常:", e);
        return getMessage(e.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleFeignException(FeignException e) {
        log.error("Feign异常:"+e.getMessage());
        return ResultHelper.getError(e.getMessage());
    }




    
    
    /**
     * exception报错内容： status 500 reading IForderClient#save(Map); content:
		{"timestamp":1520586889833,"status":500,"error":"Internal Server Error","exception":"com.chens.core.exception.BaseException","message":"已存在相同名称的目录","path":"/forder/save"}
     * @param exceptionMessage
     * @return
     */
    public Result getMessage(String exceptionMessage) {
    	int start = exceptionMessage.indexOf("{");
    	int end = exceptionMessage.indexOf("}");
    	exceptionMessage = exceptionMessage.substring(start, end+1);
    	JSONObject obj = JSONObject.parseObject(exceptionMessage);
    	Integer errCode = (Integer) obj.get("code");
    	String errMsg = (String)obj.get("message");
    	return ResultHelper.getError(errCode,errMsg);
    }
    
    
    
    
    
}
