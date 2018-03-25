package com.chens.auth.exception;

import com.chens.core.enums.IBaseEnum;
import com.chens.core.exception.BaseException;

/**
 * 用户token异常
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/22
 */
public class UserTokenException extends BaseException {

    public UserTokenException(IBaseEnum baseEnum) {
        super(baseEnum);
    }
}
