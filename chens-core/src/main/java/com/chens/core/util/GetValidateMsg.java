package com.chens.core.util;

import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 获取校验报错提示
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/16
 */
public class GetValidateMsg {


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
