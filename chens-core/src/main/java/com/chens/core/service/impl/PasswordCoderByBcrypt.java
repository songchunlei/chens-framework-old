package com.chens.core.service.impl;

import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.service.IPasswordCoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Spring-security加密
 *
 * @author songchunlei@qq.com
 * @create 2018/5/23
 */
public class PasswordCoderByBcrypt implements IPasswordCoder{
    /** spring自带加密工具
     * spring security中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密。SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的。
     （1）加密(encode)：注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中。
     （2）密码匹配(matches)：用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，然后将其与从数据库中查询到的密码hash值进行比较。如果两者相同，说明用户输入的密码正确
     */
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(CommonConstants.DEFAULT_PASSWORD_LENGTH);
    private BASE64Encoder base64en = new BASE64Encoder();

    @Override
    public String encoder(String pwd) {
        try {
            //首先使用sha512，将用户密码归一化为64字节hash值。因为两个原因：一个是Bcrypt算对输入敏感，如果用户输入的密码较长，可能导致Bcrypt计算过慢从而影响响应时间；另一个是有些Bcrypt算法的实现会将长输入直接截断为72字节，从信息论的角度讲，这导致用户信息的熵变小；
            //待验证
            return encoder.encode(base64en.encode(pwd.getBytes(CommonConstants.CHARACTER_UTF8)));
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(BaseExceptionEnum.SERVER_ERROR.getCode(),e.getMessage());
        }
    }

    @Override
    public boolean matches(String newPwd, String oldPwd) {
        try {
            newPwd = base64en.encode(newPwd.getBytes(CommonConstants.CHARACTER_UTF8));
            if(encoder.matches(newPwd,oldPwd))
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(BaseExceptionEnum.SERVER_ERROR.getCode(),e.getMessage());
        }
    }
}
