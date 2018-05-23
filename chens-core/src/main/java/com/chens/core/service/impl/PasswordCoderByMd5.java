package com.chens.core.service.impl;

import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.service.IPasswordCoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密
 *
 * @author songchunlei@qq.com
 * @create 2018/5/23
 */
public class PasswordCoderByMd5 implements IPasswordCoder{

    @Override
    public String encoder(String pwd) {
        return encoderByMd5(pwd);
    }

    @Override
    public boolean matches(String newPwd, String oldPwd) {
        if(encoderByMd5(newPwd).equals(oldPwd))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * MD5加密
     * @param str
     * @return
     */
    public String encoderByMd5(String str){
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance(CommonConstants.MD5);
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            return base64en.encode(md5.digest(str.getBytes(CommonConstants.CHARACTER_UTF8)));
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(BaseExceptionEnum.SERVER_ERROR.getCode(),e.getMessage());
        }
        catch (NoSuchAlgorithmException e) {
            throw new BaseException(BaseExceptionEnum.SERVER_ERROR.getCode(),e.getMessage());
        }
    }
}
