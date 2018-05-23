package com.chens.core.service.impl;

import com.chens.core.constants.CommonConstants;
import com.chens.core.service.IPasswordCoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring-security加密
 *
 * @author songchunlei@qq.com
 * @create 2018/5/23
 */
public class PasswordCoderByBCrypt implements IPasswordCoder{
    /** spring自带加密工具
     * spring security中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密。SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的。
     （1）加密(encode)：注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中。
     （2）密码匹配(matches)：用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，然后将其与从数据库中查询到的密码hash值进行比较。如果两者相同，说明用户输入的密码正确
     */
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(CommonConstants.DEFAULT_PASSWORD_LENGTH);


    @Override
    public String encoder(String pwd) {
        return encoder.encode(pwd);
    }

    @Override
    public boolean matches(String newPwd, String oldPwd) {
        if(encoder.matches(newPwd,oldPwd))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
