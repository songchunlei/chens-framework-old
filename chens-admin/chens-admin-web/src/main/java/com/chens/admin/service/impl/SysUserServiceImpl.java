package com.chens.admin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.core.constants.CommonConstants;
import com.chens.core.util.StringUtils;
import com.chens.core.util.ToolUtil;
import com.chens.core.vo.sys.AuthRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.admin.mapper.SysUserMapper;
import com.chens.admin.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    //spring自带加密工具
    /**
     * spring security中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密。SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的。
     （1）加密(encode)：注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中。
     （2）密码匹配(matches)：用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，然后将其与从数据库中查询到的密码hash值进行比较。如果两者相同，说明用户输入的密码正确
     */
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(CommonConstants.DEFAULT_PASSWORD_LENGTH);

    @Override
    public SysUser findByUsername(AuthRequest authRequest) throws BaseException{
        SysUser query = new SysUser();
        query.setUsername(authRequest.getUserName());
        List<SysUser> users = this.selectList(new EntityWrapper<>(query));
        if(users!=null && users.size()>0)
        {
            for (SysUser user:users) {
                if(encoder.matches(authRequest.getPassword(),user.getPassword()))
                {
                    return user;
                }
                else
                {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public boolean createAccount(SysUser sysUser) {
        //对密码加密
        String passwod = sysUser.getPassword();
        if (StringUtils.isEmpty(passwod))
        {
            //设置默认密码
            passwod = CommonConstants.DEFAULT_PASSWORD;
        }
        sysUser.setPassword(encoder.encode(passwod));
        return this.insert(sysUser);
    }

    @Override
    @Transactional
    public String restPwd(String UserId,boolean isRandom) {
        String password = CommonConstants.DEFAULT_PASSWORD;
        if(isRandom)
        {
            password = ToolUtil.getRandomString(16);
        }
        SysUser sysUser = new SysUser();
        sysUser.setPassword(password);
        sysUser.setId(UserId);
        if(this.updateById(sysUser))
        {
            return password;
        }
        return null;
    }

    @Override
    public List<SysUser> getUserListByRoleId(Page<SysUser> page, SysUser user) {
        return baseMapper.getUserListByRoleId(page,user);
    }
}
